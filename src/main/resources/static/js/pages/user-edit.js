import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";


if (window.location.pathname.includes('/manage-users/') && window.location.pathname.includes('/edit')) {
  // Use DOMContentLoaded directly in signup.js
  document.addEventListener('DOMContentLoaded', () => {

    // Attach the onSubmit function to the button click event
    const form = document.getElementById('update-user-form');
    if (form) {
      form.addEventListener('submit', onUserUpdate);
    }

  });
}

const onUserUpdate = (event) => {
  // Prevent the default form submission
  event.preventDefault();

  // Fetch Form-data
  const formData = new FormData(event.target);
  const user = Object.fromEntries(formData.entries());

  // Invoke makeRequest(url, method, formData, onSuccess, onError)
  makeRequest('/api/users/' + user.id, 'PATCH', user, onSuccess, onError);

}

// Define the onSuccess and onError functions as needed
const onSuccess = (data) => {
  showToast("Request successful!!", "success");
};

const onError = (error) => {
  showToast("Request Failed", "fail");
};

export { onUserUpdate };
