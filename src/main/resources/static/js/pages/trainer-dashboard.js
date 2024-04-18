import { uploadCertificate } from '../services/user-service.js';

if (window.location.pathname.includes('/trainer-dashboard')) {
    document.addEventListener('DOMContentLoaded', () => {
        const form = document.getElementById('upload-certificate-form');
        if(form){
            form.addEventListener('submit', (event) => handleSubmit(event));
        }
  });
}

export const handleSubmit = (event) => {
  event.preventDefault();
  const formData = new FormData(event.target);
  const certificateData = Object.fromEntries(formData.entries());
  uploadCertificate(certificateData);
}