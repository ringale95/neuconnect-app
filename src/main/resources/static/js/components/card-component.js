export const createCard = (header, body, title, text, link) => {
    return `
        <div class="card">
            <div class="card-header">${header}</div>
            <div class="card-body">
                <h5 class="card-title">${title}</h5>
                <p class="card-text">${body}</p>
                <a href="${link}" class="btn btn-primary">${text}</a>
            </div>
        </div>
    `;
}
