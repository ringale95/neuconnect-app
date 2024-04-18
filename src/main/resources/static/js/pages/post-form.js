import { createPost } from '../services/post-service';

if (window.location.pathname.includes('/create-post')) {
    document.addEventListener('DOMContentLoaded', () => {    
        const form = document.getElementById('create-post-form');

        if(form){
            form.addEventListener('submit', (event) => handleSubmit(event));
        }
  });
}

const handleSubmit = (event) => {

    // Get Form data

    // Convert to Post
    const post = {
        title: "Test Title",
        description: "Test description"
    };

    // Create Post
    createPost(post);
}

