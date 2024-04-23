// Function to render Assign button
export const renderAssignBtn = (serviceId) => {
    const assignBtn = document.createElement('a');
    assignBtn.textContent = 'Assign';
    assignBtn.href = `${serviceId}/assign`
    assignBtn.classList.add('btn', 'btn-primary');
    return assignBtn;
}

// Function to render In Progress button
export const renderInProgressBtn = (serviceId) => {
    const inProgressBtn = document.createElement('a');
    inProgressBtn.textContent = 'In Progress';
    inProgressBtn.href = `${serviceId}/in-progress`
    inProgressBtn.classList.add('btn', 'btn-warning');
    return inProgressBtn;
}

// Function to render Enroll button
export const renderEnrollBtn = (serviceId) => {
    const enrollBtn = document.createElement('a');
    enrollBtn.textContent = 'Enroll';
    enrollBtn.href = `${serviceId}/enroll`
    enrollBtn.classList.add('btn', 'btn-success');
    return enrollBtn;
}

// Function to render Complete button
export const renderCompleteBtn = (serviceId) => {
    const completeBtn = document.createElement('a');
    completeBtn.textContent = 'Complete';
    completeBtn.href = `${serviceId}/complete`
    completeBtn.classList.add('btn', 'btn-info');
    return completeBtn;
}

export const renderBtn = (service) => {
    if(service.showAssignedBtn)
        return renderAssignBtn(service.id)
    else if(service.showInProgressBtn)
        return renderInProgressBtn(service.id);
    else if(service.showCompleteBtn)
        return renderCompleteBtn(service.id);
    else if(service.showEnrollBtn)
        return renderEnrollBtn(service.id);
}

