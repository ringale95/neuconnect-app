const showToast = (message, type, duration = 3000) => {
  // Create Toast
  const toast = createToast(message, type);

  // Display Toast
  displayToast(toast, duration);
};

const createToast = (message, type) => {
  // Create a new toast element
  const toast = document.createElement('div');
  toast.classList.add('toast');

  // Set the background color based on the type
  if (type === 'success') {
    toast.classList.add('bg-success', 'text-light'); // Green background for success
  } else if (type === 'fail') {
    toast.classList.add('bg-danger', 'text-light'); // Red background for fail
  } else {
    // Default style for other types
    toast.classList.add('bg-info', 'text-light');
  }

  toast.setAttribute('role', 'alert');
  toast.setAttribute('aria-live', 'polite');
  toast.setAttribute('aria-atomic', 'true');
  toast.style.position = 'absolute';
  toast.style.top = '0';
  toast.style.right = '0';
  toast.style.marginTop = '10px';

  // Create a toast body
  const toastBody = document.createElement('div');
  toastBody.classList.add('toast-body');
  toastBody.innerText = message;

  // Append the body to the toast
  toast.appendChild(toastBody);

  // Add the toast to the page
  document.body.appendChild(toast);

  // Initialize the Bootstrap toast
  const bootstrapToast = new bootstrap.Toast(toast);

  // Return the toast element
  return bootstrapToast;
};

const displayToast = (toast, duration) => {
  // Show the toast
  toast.show();

  // Set a timeout to hide the toast after the specified duration
  setTimeout(() => {
    toast.hide();
  }, duration);
};

export { showToast };
