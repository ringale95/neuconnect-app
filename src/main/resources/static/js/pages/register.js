import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";


if (window.location.pathname.includes('/register')){
  // Use DOMContentLoaded directly in signup.js
  document.addEventListener('DOMContentLoaded', () => {

    // Attach the onSubmit function to the button click event
    const form = document.getElementById('register-form');
    if (form) {
      form.addEventListener('submit', onUserRegistration);
    }

  });
}

const onUserRegistration = (event) => {
  // Prevent the default form submission
  event.preventDefault();

  // Fetch Form-data
  const formData = new FormData(event.target);
  const registerData = Object.fromEntries(formData.entries());

  // Invoke makeRequest(url, method, formData, onSuccess, onError)
  makeRequest('/register', 'POST', registerData, onSuccess, onError);

}

// Define the onSuccess and onError functions as needed
const onSuccess = (data) => {
  showToast("Registration successfull!!", "success");
};

const onError = (error) => {
  showToast("Request Failed", "fail");
};

export { onUserRegistration };
