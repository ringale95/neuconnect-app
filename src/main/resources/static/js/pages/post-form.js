import { createPost } from '../services/post-service.js';

if (window.location.pathname.includes('/create-post')) {
    document.addEventListener('DOMContentLoaded', () => {    
        const form = document.getElementById('create-post-form');

        if(form){
            form.addEventListener('submit', (event) => handleSubmit(event));
        }
  });
}

const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const post = Object.fromEntries(formData.entries());
    post.tags = post.tags.split(',');
    createPost(post);
}

