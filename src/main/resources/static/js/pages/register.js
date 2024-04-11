import { makeRequest } from "../commons/api.js";
import { showToast } from "../commons/toast.js";

// Wait for page to load using DOMContentLoaded
if (window.location.pathname.includes('/neu-connect/register')){
    document.addEventListener('DOMContentLoaded', () => {
        const form = document.getElementById("register-form");

        // Add event listener on form
        form.addEventListener('submit', onSubmit);
    });
}

const onSubmit = (event) => {
    event.preventDefault();

    // Fetch Form-data
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    // Invoke makeRequest(url, method, data, onSuccess, onError)
    makeRequest("/api/users", "POST", data, onSuccess, onError);
}

const onSuccess = (response) => {
     showToast("User registered successfully!!", "success");
}

const onError = (response) => {
     showToast("Request Failed", "fail");
}

export { onSubmit };
