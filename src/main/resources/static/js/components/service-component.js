export const getServiceCard = (service) => {

    const card = document.createElement("div");
    card.classList.add("card", "mb-3");

    const cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    const title = document.createElement("h5");
    title.classList.add("card-title");
    title.textContent = service.title == "" ? "Service Title": service.title;


    const description = document.createElement("p");
    description.classList.add("card-text");
    description.textContent = service.description == "" ? "Description": service.description;

    const karma = document.createElement("p");
    karma.classList.add("card-text");
    karma.textContent = "Karma: " + service.karma + " points.";

    const author = document.createElement("p");
    author.classList.add("card-text");
    author.textContent = "Author: " + service.author.fname;

    cardBody.appendChild(title);
    cardBody.appendChild(description);
    if(service.karma > 0) cardBody.appendChild(karma);
    cardBody.appendChild(author);
    card.appendChild(cardBody);

    return card;
}