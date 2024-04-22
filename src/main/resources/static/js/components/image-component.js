// Function to create an image element
export const createImageElement = (src, alt) => {
    const img = document.createElement('img');
    img.setAttribute('src', src);
    img.classList.add('rounded-circle');
    img.style.width = '40px';
    img.style.height = '40px';
    img.alt = alt;
    return img;
}
