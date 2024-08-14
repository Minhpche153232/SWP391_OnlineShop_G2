<%-- 
    Document   : change-password
    Created on : Aug 10, 2024, 1:08:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/changePassword.css">
<script>
    function  check() {
        let btnChange = document.getElementById("change");
        let oldPassword = document.getElementById("oldPassword");
        let newPassword = document.getElementById("newPassword");
        let confirmNewPassword = document.getElementById("confirmNewPassword");
        
        
        if (oldPassword.value && newPassword.value && oldPassword.value === newPassword.value) {
            btnChange.disabled = false;
            newPassword.style.borderColor = "red";
            oldPassword.style.borderColor = "red";

        } else {
            newPassword.style.borderColor = "";
            oldPassword.style.borderColor = "";
        }
        if (newPassword.value && confirmNewPassword.value) {
            if (newPassword.value !== confirmNewPassword.value) {
                confirmNewPassword.style.borderColor = "red";
                btnChange.disabled = true;

            } else {
                confirmNewPassword.style.borderColor = "";

                if (oldPassword.value && newPassword.value && confirmNewPassword.value) {
                    btnChange.disabled = false;


                } else {
                    btnChange.disabled = true;

                }
            }
        }
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
        <form  action="ChangePassword" method="post" onchange="check()">
            <div class="modal-content" >
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> Change password</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" style="display: flex; justify-content: center" >
                    <div class="form">
                        <div class="input-container" >
                            <input type="password" name="oldPassword"  id="oldPassword" placeholder="Enter old password" onchange="check()">
                        </div>
                        <div class="input-container">
                            <input type="password" name="newPassword" id="newPassword"  placeholder="Enter new password" onchange="check()">
                        </div>
                        <div class="input-container">
                            <input type="password" name="confirmNewPassword" id="confirmNewPassword" placeholder="Enter comfirm new password" onchange="check()">
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