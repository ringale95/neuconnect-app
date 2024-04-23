
// Function to check if scrolled halfway through the webpage
export const isHalfwayScrolled = () => {
  // Calculate the halfway point of the webpage
  const halfwayPoint = window.innerHeight + window.scrollY >= document.body.offsetHeight / 2;
  return halfwayPoint;
}

// Callback function to be triggered when halfway scrolled
export const halfwayScrollCallback = () => {
  console.log("You've scrolled halfway through the webpage!");
}