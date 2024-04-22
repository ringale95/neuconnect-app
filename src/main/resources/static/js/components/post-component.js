// Importing reusable functions from separate files
import { createLikeButton, createCommentButton, createDislikeButton} from './button-component.js';
import { createCardElement, createCardBody, createCardHeader, createCardFooter } from './card-component.js';

// Function to render a post
export const renderPost = (post) => {
    const card = createCardElement();
    const cardHeader = createCardHeader(post.author);
    const cardBody = createCardBody(post);
    const cardFooter = createCardFooter();
    const likeButton = createLikeButton(post.id, post.upvotes);
    const dislikeButton = createDislikeButton(post.id, post.upvotes);
    const commentButton = createCommentButton();

    if(post.liked)
        cardFooter.appendChild(dislikeButton);
    else
        cardFooter.appendChild(likeButton);
    
    cardFooter.appendChild(commentButton);

    card.appendChild(cardHeader);
    card.appendChild(cardBody);
    card.appendChild(cardFooter);

    return card;
};
