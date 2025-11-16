const API_URL = '/api';

function showAlert(containerId, message, type = 'danger') {
  const container = document.getElementById(containerId);
  container.innerHTML = `
    <div class="alert alert-${type} alert-dismissible fade show mt-3">
      ${message}
      <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>`;
  setTimeout(() => container.innerHTML = '', 5000);
}

function getToken() {
  return localStorage.getItem('jwt');
}

function setToken(token) {
  localStorage.setItem('jwt', token);
}

function logout() {
  localStorage.removeItem('jwt');
  window.location.href = '/login.html';
}

document.getElementById('loginForm')?.addEventListener('submit', async (e) => {
  e.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  try {
    const response = await fetch(`${API_URL}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });

    const data = await response.json();

    if (response.ok) {
      setToken(data.token);
      window.location.href = '/productos.html';
    } else {
      showAlert('alertContainer', data || 'Credenciales incorrectas');
    }
  } catch (error) {
    showAlert('alertContainer', 'Error de conexión');
  }
});

if (window.location.pathname.includes('productos.html') && !getToken()) {
  window.location.href = '/login.html';
}