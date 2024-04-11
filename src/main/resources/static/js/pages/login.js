import { makeRequest } from "../commons/api.js";

// Wait for page to load using DOMContentLoaded
if (window.location.pathname.includes('/neu-connect/login')) {
    document.addEventListener('DOMContentLoaded', () => {
        const form =  document.getElementById("login-form");

        // Add event listener on form
        form.addEventListener('submit', onSubmitLogin);
    });
}

const onSubmitLogin = (event) => {
    event.preventDefault();

    // Fetch Form-data
    const formData = new FormData(event.target);
    const credentials = Object.fromEntries(formData.entries());

    // Convert form data to JSON

    // Invoke makeRequest(url, method, data, onSuccess, onError)
    makeRequest("/neu-connect/login", "POST", credentials, onSuccess, onError);
}

const onSuccess = (response) => {
    //showToast("Request successful!!", "success");

}
const onError = (response) => {
  //showToast("Request Failed", "fail");
}

export { onSubmitLogin }
