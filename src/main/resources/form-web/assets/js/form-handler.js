document.getElementById('footer').innerHTML =
    '© ' + new Date().getFullYear() + ' Juan Martin Ruiz | ALL RIGHTS RESERVED.';

// Track form validation state
const formState = {
    firstName: false,
    lastName: false,
    email: false,
    gender: false,
    mobile: false,
    isValid: function () {
        return this.firstName && this.lastName && this.email && this.gender && this.mobile;
    }
};

// Update submit button state
function updateSubmitButton() {
    document.getElementById('submitButton').disabled = !formState.isValid();
}

document.getElementById('firstName').addEventListener('input', validateFirstName);
document.getElementById('firstName').addEventListener('blur', validateFirstName);

document.getElementById('lastName').addEventListener('input', validateLastName);
document.getElementById('lastName').addEventListener('blur', validateLastName);

document.getElementById('email').addEventListener('input', validateEmail);
document.getElementById('email').addEventListener('blur', validateEmail);

document.getElementById('mobile').addEventListener('input', validateMobile);
document.getElementById('mobile').addEventListener('blur', validateMobile);

// Add event listeners to gender radio buttons
const genderInputs = document.querySelectorAll('input[name="gender"]');
genderInputs.forEach(input => {
    input.addEventListener('change', validateGender);
});

// Field validation functions
function validateFirstName() {
    const firstName = document.getElementById('firstName');
    clearFieldMessages(firstName);

    if (!firstName.value.trim()) {
        showError(firstName, 'First Name is required');
        formState.firstName = false;
    } else {
        formState.firstName = true;
    }

    updateSubmitButton();
}

function validateLastName() {
    const lastName = document.getElementById('lastName');
    clearFieldMessages(lastName);

    if (!lastName.value.trim()) {
        showError(lastName, 'Last Name is required');
        formState.lastName = false;
    } else {
        formState.lastName = true;
    }

    updateSubmitButton();
}

function validateEmail() {
    const email = document.getElementById('email');
    clearFieldMessages(email);

    if (!email.value.trim()) {
        showError(email, 'Email is required');
        formState.email = false;
    } else if (!isValidEmail(email.value)) {
        showError(email, 'Please enter a valid email address');
        formState.email = false;
    } else {
        formState.email = true;
    }

    updateSubmitButton();
}

function validateMobile() {
    const mobile = document.getElementById('mobile');
    clearFieldMessages(mobile);

    if (!mobile.value.trim()) {
        showError(mobile, 'Mobile Number is required');
        formState.mobile = false;
    } else if (!isValidMobile(mobile.value)) {
        showError(mobile, 'Mobile number must be 10 digits');
        formState.mobile = false;
    } else {
        formState.mobile = true;
    }

    updateSubmitButton();
}

function validateGender() {
    const genderContainer = document.querySelector('.radio-group');
    clearContainerMessages(genderContainer);

    const genderSelected = document.querySelector('input[name="gender"]:checked');
    if (!genderSelected) {
        showErrorOnContainer(genderContainer, 'Please select a gender');
        formState.gender = false;
    } else {
        formState.gender = true;
    }

    updateSubmitButton();
}

// Helper validation functions
function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function isValidMobile(mobile) {
    const digitsOnly = mobile.replace(/\D/g, '');
    return digitsOnly.length === 10;
}

// UI helper functions
function showError(element, message) {
    // Create error message element
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;

    // Add error message after the element
    element.parentNode.insertBefore(errorDiv, element.nextSibling);
}

function showErrorOnContainer(container, message) {
    // Create error message element
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;

    // Add error message to the container
    container.appendChild(errorDiv);
}

function clearFieldMessages(element) {
    // Find parent node
    const parentNode = element.parentNode;

    // Remove any existing error messages
    const errorMessages = parentNode.querySelectorAll('.error-message');
    errorMessages.forEach(message => message.remove());
}

function clearContainerMessages(container) {
    // Remove error messages
    const errorMessages = container.querySelectorAll('.error-message');
    errorMessages.forEach(message => message.remove());
}

// Toast notification function
function showToast(message, type = 'success', duration = 3000) {
    const toastContainer = document.getElementById('toastContainer');

    // Create toast element
    const toast = document.createElement('div');
    toast.className = 'toast';

    // Set background color based on type
    if (type === 'success') {
        toast.style.backgroundColor = '#22c55e'; // Green
    } else if (type === 'error') {
        toast.style.backgroundColor = '#ef4444'; // Red
    } else if (type === 'warning') {
        toast.style.backgroundColor = '#f59e0b'; // Amber
    } else if (type === 'info') {
        toast.style.backgroundColor = '#3b82f6'; // Blue
    }

    // Create icon based on type
    const toastIcon = document.createElement('div');
    toastIcon.className = 'toast-icon';

    let iconSvg = '';
    if (type === 'success') {
        iconSvg = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M20.285 2l-11.285 11.567-5.286-5.011-3.714 3.716 9 8.728 15-15.285z"/></svg>';
    } else if (type === 'error') {
        iconSvg = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"/></svg>';
    } else if (type === 'warning') {
        iconSvg = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M12 1l-12 22h24l-12-22zm-1 8h2v7h-2v-7zm1 11.25c-.69 0-1.25-.56-1.25-1.25s.56-1.25 1.25-1.25 1.25.56 1.25 1.25-.56 1.25-1.25 1.25z"/></svg>';
    } else if (type === 'info') {
        iconSvg = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"><path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-.001 5.75c.69 0 1.251.56 1.251 1.25s-.561 1.25-1.251 1.25-1.249-.56-1.249-1.25.559-1.25 1.249-1.25zm2.001 12.25h-4v-1c.484-.179 1-.201 1-.735v-4.467c0-.534-.516-.618-1-.797v-1h3v6.265c0 .535.517.558 1 .735v.999z"/></svg>';
    }

    toastIcon.innerHTML = iconSvg;

    // Create message element
    const toastMessage = document.createElement('div');
    toastMessage.className = 'toast-message';
    toastMessage.textContent = message;

    // Assemble toast
    toast.appendChild(toastIcon);
    toast.appendChild(toastMessage);

    // Add toast to container
    toastContainer.appendChild(toast);

    // Trigger animation after a small delay to ensure the toast is properly rendered
    setTimeout(() => {
        toast.classList.add('show');
    }, 10);

    // Set timer to remove toast
    setTimeout(() => {
        toast.classList.remove('show');

        // Wait for fade out animation to complete before removing from DOM
        setTimeout(() => {
            toast.remove();
        }, 300);
    }, duration);
}

// Form submission with validation
document.getElementById('studentRegistrationForm').addEventListener('submit', function (e) {
    e.preventDefault();

    // Do a final validation check (just in case)
    validateFirstName();
    validateLastName();
    validateEmail();
    validateGender();
    validateMobile();

    // If the form is valid, process the submission
    if (formState.isValid()) {
        // Gather form data
        const formData = {
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            gender: document.querySelector('input[name="gender"]:checked')?.value || '',
            mobile: document.getElementById('mobile').value,
            dateOfBirth: document.getElementById('dateOfBirth').value,
            subjects: document.getElementById('subjects').value,
            hobbies: Array.from(document.querySelectorAll('input[name="hobbies"]:checked')).map(el => el.value),
            currentAddress: document.getElementById('currentAddress').value,
            country: document.getElementById('country').value,
            remember: document.getElementById('remember').checked
        };

        // Display form data
        console.log('Form submitted with data:', formData);

        // Show success toast instead of alert
        showToast('Form submitted successfully!', 'success', 4000);

        // Optionally, reset the form
        // this.reset();
        // clearFieldMessages();
    }
});

// Run initial validation to check the initial state of the form
validateFirstName();
validateLastName();
validateEmail();
validateGender();
validateMobile();