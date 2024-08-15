<%-- 
    Document   : change-password
    Created on : Aug 10, 2024, 1:08:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/changePassword.css">
<script>
    function check() {
        let btnChange = document.getElementById("change");
        let oldPassword = document.getElementById("oldPassword");
        let newPassword = document.getElementById("newPassword");
        let confirmNewPassword = document.getElementById("confirmNewPassword");
        let passwordError = document.getElementById("passwordError");
        let confirmPasswordError = document.getElementById("confirmPasswordError");

        // Xóa thông báo lỗi trước khi kiểm tra
        passwordError.innerText = '';
        confirmPasswordError.innerText = '';
        
        // Điều kiện kiểm tra mật khẩu mới
        const passwordIsValid = validateNewPassword(newPassword.value);

        if (oldPassword.value && newPassword.value && oldPassword.value === newPassword.value) {
            btnChange.disabled = true;
            newPassword.style.borderColor = "red";
            oldPassword.style.borderColor = "red";
            passwordError.innerText = 'New password cannot be the same as the old password.';
        } else {
            newPassword.style.borderColor = "";
            oldPassword.style.borderColor = "";
        }

        if (newPassword.value && confirmNewPassword.value) {
            if (!passwordIsValid) {
                btnChange.disabled = true;
                newPassword.style.borderColor = "red";
                passwordError.innerText = 'Please fix the password errors before proceeding.';
            } else if (newPassword.value !== confirmNewPassword.value) {
                confirmNewPassword.style.borderColor = "red";
                confirmPasswordError.innerText = 'Confirm password does not match.';
                btnChange.disabled = true;
            } else {
                confirmNewPassword.style.borderColor = "";
                confirmPasswordError.innerText = '';

                // Nếu mọi thứ hợp lệ, bật nút Change Password
                if (oldPassword.value && passwordIsValid && confirmNewPassword.value) {
                    btnChange.disabled = false;
                } else {
                    btnChange.disabled = true;
                }
            }
        }
    }

    function validateNewPassword(password) {
        const passwordError = document.getElementById("passwordError");

        // Kiểm tra điều kiện phức tạp của mật khẩu
        if (password.length < 8 || password.length > 15) {
            passwordError.innerText = 'Password must be 8-15 characters long.';
            return false;
        }
        if (!/[A-Z]/.test(password)) {
            passwordError.innerText = 'Password must contain at least one uppercase letter.';
            return false;
        }
        if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
            passwordError.innerText = 'Password must contain at least one special character.';
            return false;
        }
        if (/\s/.test(password)) {
            passwordError.innerText = 'Password cannot contain spaces.';
            return false;
        }

        passwordError.innerText = '';
        return true;
    }
</script>

<!-- Button trigger modal -->
<div style="width: 100%; display: flex; justify-content: center; align-items: center; padding: 10px; margin-left:-50px; margin-top: -70px">
    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="width: 250px">
    Change password
</button>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="ChangePassword" method="post" onchange="check()">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Change password</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" style="display: flex; justify-content: center">
                    <div class="form">
                        <div class="input-container">
                            <input type="password" name="oldPassword" id="oldPassword" placeholder="Enter old password" onchange="check()">
                        </div>
                        <div class="input-container">
                            <input type="password" name="newPassword" id="newPassword" placeholder="Enter new password" onchange="check()">
                            <div id="passwordError" style="color: red; font-size: 12px;"></div>
                        </div>
                        <div class="input-container">
                            <input type="password" name="confirmNewPassword" id="confirmNewPassword" placeholder="Enter confirm new password" onchange="check()">
                            <div id="confirmPasswordError" style="color: red; font-size: 12px;"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" disabled id="change">Save changes</button>
                </div>
            </div>
        </form>
    </div>
</div>
