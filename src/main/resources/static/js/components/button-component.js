import { upvoteThisPost, downvoteThisPost, addCommentToPost } from '../services/post-service.js';
import { displayComments } from './comment-component.js';
let commentVisible = false;

export const createButtonElement = (icon, upvotes, id, onSuccess, onClick) => {
    const outerDiv = document.createElement('span');
    const button = createButton(icon, upvotes, id, onSuccess, onClick);
    outerDiv.appendChild(button);
    outerDiv.id = `post-action-button-${id}`
    return outerDiv;
}

const createButton = (icon, upvotes, id, onSuccess, onClick) => {
    const button = document.createElement('div');
    button.classList.add('flex-fill', 'text-center', 'border', 'p-2');
    button.style.height = '100%';
    button.innerHTML = icon + " ";
    button.appendChild(onSuccess(upvotes, id))
    button.onclick = (event) => onClick(event,id);
    return button;
}

const onClickLike = (event, id) => {
    event.stopPropagation();
    upvoteThisPost(id, changeUpvoteCount);
}

const onClickDislike = (event, id) => {
    event.stopPropagation();
    downvoteThisPost(id, changeDownvoteCount);
}

const onClickComment = (event, id) => {
    event.stopPropagation();
    if (!commentVisible) {
        createCommentSection(id);
        commentVisible = true;
    } else {
        hideCommentSection(id);
        commentVisible = false;
    }
}

const hideCommentSection = (postId) => {
    const postCard = document.getElementById(`post-action-button-${postId}`).closest(".card");
    const commentSection = postCard.querySelector(".comment-section");
    if (commentSection) {
        commentSection.remove();
    }
}

const renderUpvoteCount = (upvotes, id) => {
    const upvote = document.createElement("span");
    upvote.id = `post-upvote-${id}`;
    upvote.innerText = upvotes;
    return upvote;
}

const changeUpvoteCount = ({ postId, currentUpvotes }) => {
    const outerDiv = document.getElementById(`post-action-button-${postId}`);
    outerDiv.innerHTML = '';
    outerDiv.appendChild(createDislikeButton(postId, currentUpvotes));
}

const changeDownvoteCount = ({ postId, currentUpvotes }) => {
    const outerDiv = document.getElementById(`post-action-button-${postId}`);
    outerDiv.innerHTML = '';
    outerDiv.appendChild(createLikeButton(postId, currentUpvotes));
}

export const createLikeButton = (id, upvotes) => {
    return createButtonElement(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-circle" viewBox="0 0 16 16">
    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8m15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707z"/>
  </svg>`, upvotes, id, renderUpvoteCount, onClickLike);
};

export const createDislikeButton = (id, upvotes) => {
    return createButtonElement(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8m15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293z"/>
  </svg>`, upvotes, id, renderUpvoteCount, onClickDislike);
};

export const createCommentButton = (id) => {
    const commentButton = createButtonElement(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
        class="bi bi-chat" viewBox="0 0 16 16">
        <path
            d="M2.678 11.894a1 1 0 0 1 .287.801 11 11 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8 8 0 0 0 8 14c3.996 0 7-2.807 7-6s-3.004-6-7-6-7 2.808-7 6c0 1.468.617 2.83 1.678 3.894m-.493 3.905a22 22 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a10 10 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105" />
    </svg>`, 'Comment', id, (u, i) => {
        const commentText = document.createElement('span');
        commentText.innerText = "Comment";
        return commentText;
    }, onClickComment);
    return commentButton;
};

const createCommentSection = (postId) => {
    const postCard = document.getElementById(`post-action-button-${postId}`).closest(".card");
    hideCommentSection(postId);

    const commentSection = document.createElement("div");
    commentSection.classList.add("comment-section", "mt-3", "border", "p-3", "rounded");

    const commentInput = document.createElement("input");
    commentInput.setAttribute("type", "text");
    commentInput.setAttribute("placeholder", "Write a comment...");
    commentInput.classList.add("form-control");

    const commentButton = document.createElement("button");
    commentButton.textContent = "Comment";
    commentButton.classList.add("btn", "btn-secondary", "mt-2");
    commentButton.onclick = (event) => {
        event.stopPropagation();  
        const commentText = commentInput.value;      
        if (commentText.trim() !== "") {
            addCommentToPost(postId, { comment: commentText }, (data) => {
                const container = document.getElementById(`comment-container-${postId}`);
                container.innerHTML = '';
                displayComments(postId)});
                commentInput.value = "";
        } else {
            alert("Please enter a comment before submitting.");
        }
    };

    commentSection.appendChild(commentInput);
    commentSection.appendChild(commentButton);
    const commentContainer = document.createElement('div');
    commentContainer.id = `comment-container-${postId}`;
    commentSection.appendChild(commentContainer);
    displayComments(postId);
    postCard.appendChild(commentSection);
};

