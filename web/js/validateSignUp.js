function validateField(id, regex, errorMessage) {
    const field = document.getElementById(id);
    const errorDiv = document.getElementById(id + 'Error');
    if (!regex.test(field.value)) {
        errorDiv.innerText = errorMessage;
        return false;
    } else {
        errorDiv.innerText = '';
        return true;
    }
}



document.getElementById('fullname').addEventListener('blur', function () {
    validateField('fullname', /^[A-Za-zÀ-ỹ\s]+$/, 'Full name should contain only alphabets and spaces.');
});

document.getElementById('email').addEventListener('blur', function () {
    validateField('email', /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, 'Invalid email format.');
});

document.getElementById('phonenumber').addEventListener('blur', function () {
    validateField('phonenumber', /^[0-9]{7,11}$/, 'Phone number must be 7-11 digits long and contain only numbers.');
});

document.getElementById('address').addEventListener('blur', function () {
    validateField('address', /^[A-Za-zÀ-ỹ0-9,.() \-]+$/, 'Address contains invalid characters.');
});

/*--document.getElementById('password').addEventListener('blur', function () {
    validateField('password', /^.{8,15}$/, 'Password must be 8-15 characters long.');
});--*/
document.getElementById('password').addEventListener('blur', function () {
    const password = document.getElementById('password').value;
    const errorDiv = document.getElementById('passwordError');  // Lấy phần tử để hiển thị lỗi
    
    // Reset nội dung thông báo lỗi trước khi kiểm tra
    errorDiv.innerText = '';

    // Điều kiện 1: Độ dài 8-15 ký tự
    if (password.length < 8 || password.length > 15) {
        errorDiv.innerText = 'Password must be 8-15 characters long.';
    }
    // Điều kiện 2: Ít nhất một chữ in hoa
    else if (!/[A-Z]/.test(password)) {
        errorDiv.innerText = 'Password must contain at least one uppercase letter.';
    }
    // Điều kiện 3: Ít nhất một ký tự đặc biệt
    else if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
        errorDiv.innerText = 'Password must contain at least one special character.';
    }
    // Điều kiện 4: Không có dấu cách
    else if (/\s/.test(password)) {
        errorDiv.innerText = 'Password cannot contain spaces.';
    } 
    else {
        // Nếu không có lỗi, password hợp lệ
        errorDiv.innerText = '';  // Xóa thông báo lỗi nếu hợp lệ
    }
});



document.getElementById('confirmpassword').addEventListener('blur', function () {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmpassword').value;
    const errorDiv = document.getElementById('confirmpasswordError');
    if (password !== confirmPassword) {
        errorDiv.innerText = 'Password and Confirm Password do not match.';
    } else {
        errorDiv.innerText = '';
    }
});

function validateForm() {
    let isValid = true;
    isValid &= validateField('fullname', /^[A-Za-zÀ-ỹ\s]+$/, 'Full name should contain only alphabets and spaces.');
    isValid &= validateField('email', /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, 'Invalid email format.');
    isValid &= validateField('phonenumber', /^[0-9]{7,11}$/, 'Phone number must be 7-11 digits long and contain only numbers.');
    isValid &= validateField('address', /^[A-Za-zÀ-ỹ0-9,.() \-]+$/, 'Address contains invalid characters.');
    //isValid &= validateField('password', /^.{8,15}$/, 'Password must be 8-15 characters long.');
    const password = document.getElementById('password').value;
    const errorDiv = document.getElementById('passwordError');
    errorDiv.innerText = '';

    // Kiểm tra mật khẩu
    if (password.length < 8 || password.length > 15) {
        errorDiv.innerText = 'Password must be 8-15 characters long.';
        isValid = false;
    }
    else if (!/[A-Z]/.test(password)) {
        errorDiv.innerText = 'Password must contain at least one uppercase letter.';
        isValid = false;
    }
    else if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
        errorDiv.innerText = 'Password must contain at least one special character.';
        isValid = false;
    }
    else if (/\s/.test(password)) {
        errorDiv.innerText = 'Password cannot contain spaces.';
        isValid = false;
    }
    const gender = document.querySelector('input[name="gender"]:checked');
    if (!gender) {
        document.getElementById('genderError').innerText = 'Please select a gender.';
        isValid = false;
    } else {
        document.getElementById('genderError').innerText = '';
    }

    
    const confirmPassword = document.getElementById('confirmpassword').value;
    if (password !== confirmPassword) {
        document.getElementById('confirmpasswordError').innerText = 'Password and Confirm Password do not match.';
        isValid = false;
    } else {
        document.getElementById('confirmpasswordError').innerText = '';
    }
    
    return !!isValid;
}