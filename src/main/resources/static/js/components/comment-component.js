import { getCommentsOnPost } from '../services/post-service.js';

const injectCommentsInContainer = ({records, postId}) => {
    const commentContainer = document.getElementById(`comment-container-${postId}`);
    records.forEach(comment => {
        const commentCard = constructComment(comment);
        commentContainer.appendChild(commentCard);
    });
}

const constructComment = ({comment, author, likes}) => {
    const commentCard = document.createElement("div");
    commentCard.classList.add("card", "p-3", "mb-2", "border","mt-2","rounded","bg-dark-subtle");

    const grouperDiv = document.createElement("div");
    grouperDiv.classList.add("d-flex","position-relative");
    
    const commentBody = document.createElement("div");
    
    const commentText = document.createElement("p");
    commentText.innerHTML = ` ${comment}`; // Space added before ${comment}
    commentText.classList.add("mb-1");

    const commentAuthor = document.createElement("strong");
    commentAuthor.innerHTML = ` ${author.fname + " " + author.lname}`; // Space added before ${author.fname}
    
    const anchor = document.createElement('a');
    anchor.href = "/user-dashboard/user-profile/" + author.id;
    anchor.appendChild(commentAuthor);

    const image = document.createElement("img");
    image.classList.add("rounded-circle");
    image.style = "width: 35px; height: 35px;";
    image.src = author.profilepicPath;

    commentBody.appendChild(anchor);
    commentBody.appendChild(commentText);

    grouperDiv.appendChild(image);
    grouperDiv.appendChild(commentBody);
    
    commentCard.appendChild(grouperDiv);

    return commentCard;
}


export const displayComments = (postId) =>{
    getCommentsOnPost(postId, injectCommentsInContainer);
}
