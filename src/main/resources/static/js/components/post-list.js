import { renderPost } from "./post-component.js";
export const renderPostList = (posts) => {
    const postListContainer = document.createElement('div');
    postListContainer.innerHTML='';
    posts.forEach(post => {
        const postCard = renderPost(post);
        postListContainer.appendChild(postCard);
    });

    return postListContainer;
}


