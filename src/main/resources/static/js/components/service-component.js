import { renderBtn } from './service-component-button.js';
export const getServiceCard = (service) => {

    const card = document.createElement("div");
    card.classList.add("card", "mb-3");

    const cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    const title = document.createElement("h5");
    title.classList.add("card-title");
    title.textContent = service.title == "" ? "Service Title": service.title;

    const anchor = document.createElement('a');
    anchor.href="/services/service-status/" + service.id;
    anchor.appendChild(title);

    const description = document.createElement("p");
    description.classList.add("card-text");
    description.textContent = service.description == "" ? "Description": service.description;

    const karma = document.createElement("p");
    karma.classList.add("card-text");
    karma.textContent = "Karma: " + service.karma + " points.";

    const author = document.createElement("p");
    author.classList.add("card-text");
    author.textContent = "Author: " + service.author.fname;

    const type = document.createElement("p");
    type.classList.add("card-text");
    type.textContent = "Type of Service: " + service.type;
    
    let button;
    if(service.showAssignedBtn || service.showInProgressBtn || service.showCompleteBtn || service.showEnrollBtn){
        button = renderBtn(service);
    }

    cardBody.appendChild(anchor);
    cardBody.appendChild(description);
    if(service.karma > 0) cardBody.appendChild(karma);
    cardBody.appendChild(author);
    if(service.showAssignedBtn || service.showInProgressBtn || service.showCompleteBtn || service.showEnrollBtn){
        cardBody.appendChild(button);
    }
    card.appendChild(cardBody);

    return card;
}