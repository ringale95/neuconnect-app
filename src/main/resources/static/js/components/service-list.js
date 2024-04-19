import { getServiceCard } from "./service-component.js";

export const renderServiceList = (response) => {
    const serviceList = document.getElementById("serviceListEntryPointForJS");
    serviceList.innerHTML = '';

    const services = response.records;

    if(services.length === 0){
        const title = document.createElement("h5");
        title.classList.add("display-4");
        title.textContent = "No Services Found!";
        serviceList.appendChild(title);
    } else {
        services.forEach( service => {
            const serviceCard = getServiceCard(service);
            serviceList.appendChild(serviceCard);
        });
    }

    // Add pageNumber & pageCount to DOM
    const pageNumber = document.getElementById("pageNumber");
    pageNumber.textContent = response.pageNumber;

    const pageCount = document.getElementById("pageCount");
    pageCount.textContent = response.totalPages;

}

