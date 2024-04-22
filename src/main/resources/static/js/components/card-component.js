import { createImageElement } from './image-component.js';
import { createTextNode } from './text-component.js';

export const createCardElement = () => {
    const card = document.createElement('div');
    card.classList.add('card', 'mt-2');
    return card;
};

// Function to create the card header
export const createCardHeader = (author) => {
    const cardHeader = document.createElement('div');
    cardHeader.classList.add('card-header');

    // Create image element for profile picture
    const img = createImageElement(author.profilepicPath, 'Profile Picture');

    // Create text node for username
    const username = createTextNode(" "+author.fname + ' ' + author.lname);
    cardHeader.appendChild(img);
    cardHeader.appendChild(username);

    return cardHeader;
};

// Function to create the card body
export const createCardBody = (post) => {
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    // Create card title
    const cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = post.title;

    // Create card text
    const cardText = document.createElement('p');
    cardText.classList.add('card-text');
    cardText.textContent = post.content;

    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardText);

    return cardBody;
};

// Function to create the card footer
export const createCardFooter = () => {
    const cardFooter = document.createElement('div');
    cardFooter.classList.add('card-footer', 'd-flex', 'justify-content-center');
    cardFooter.style.padding = '0px';
    return cardFooter;
};