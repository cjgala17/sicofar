document.getElementById('stockForm')?.addEventListener('submit', async (e) => {
  e.preventDefault();
  const id = document.getElementById('productoId').value;
  const nuevoStock = document.getElementById('nuevoStock').value;

  try {
    const response = await fetch(`${API_URL}/productos/${id}/stock`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getToken()}`
      },
      body: JSON.stringify({ nuevoStock: parseInt(nuevoStock) })
    });

    if (response.ok) {
      showAlert('alertStock', 'Stock actualizado', 'success');
      cargarProductos();
    } else {
      const error = await response.text();
      showAlert('alertStock', error || 'Error');
    }
  } catch (error) {
    showAlert('alertStock', 'Error de conexión');
  }
});

async function verificarRol() {
  try {
    const token = getToken();
    const payload = JSON.parse(atob(token.split('.')[1]));
    const roles = payload.rol || [];
    if (roles.some(r => r.authority === 'ROLE_ADMIN')) {
      document.getElementById('btnCrear').style.display = 'inline-block';
    }
  } catch (e) {}
}

async function crearProducto() {
  const nombre = document.getElementById('nuevoNombre').value;
  const descripcion = document.getElementById('nuevaDescripcion').value;
  const precio = document.getElementById('nuevoPrecio').value;
  const stock = document.getElementById('nuevoStockInicial').value;

  try {
    const response = await fetch(`${API_URL}/productos`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getToken()}`
      },
      body: JSON.stringify({ nombre, descripcion, stock: parseInt(stock), precio: parseFloat(precio) })
    });

    if (response.ok) {
      bootstrap.Modal.getInstance(document.getElementById('modalCrear')).hide();
      showAlert('alertStock', 'Producto creado', 'success');
      cargarProductos();
    } else {
      showAlert('alertStock', 'Error al crear');
    }
  } catch (error) {
    showAlert('alertStock', 'Error');
  }
}

async function cargarProductos() {
  try {
    const response = await fetch(`${API_URL}/productos`, {
      headers: { 'Authorization': `Bearer ${getToken()}` }
    });

    if (response.ok) {
      const productos = await response.json();
      const tbody = document.querySelector('#tablaProductos tbody');
      tbody.innerHTML = '';
      if (productos.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" class="text-center text-muted">No hay productos</td></tr>';
      } else {
        productos.forEach(p => {
          const tr = document.createElement('tr');
          tr.innerHTML = `
            <td>${p.id}</td>
            <td>${p.nombre}</td>
            <td><span class="badge bg-${p.stock > 10 ? 'success' : p.stock > 0 ? 'warning' : 'danger'}">${p.stock}</span></td>
            <td>S/ ${p.precio.toFixed(2)}</td>
          `;
          tbody.appendChild(tr);
        });
      }
    }
  } catch (error) {
    console.error(error);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  if (window.location.pathname.includes('productos.html')) {
    cargarProductos();
    verificarRol();
    setInterval(cargarProductos, 15000);
  }
});