<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>BootstrapValidator demo</title>

    <link rel="stylesheet" href="${ctx}/assets/bootstrapvalidator/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctx}/assets/bootstrapvalidator/dist/css/bootstrapValidator.css"/>

    <script type="text/javascript" src="${ctx}/assets/bootstrapvalidator/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header" align="left">
                    <h2>用户注册</h2>
                </div>

                <form id="signupForm" method="post" class="form-horizontal" action="target.php">
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户名：</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="full_name" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">密码：</label>
                        <div class="col-md-5">
                            <input type="password" class="form-control" name="password" />
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">duanx：</label>
                        <div class="col-md-5">
                            <input type="password" class="form-control" name="confirm_password" />
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                           <span class="help-block" style="margin-left: 180px">还没有账户?  <a>注册</a></span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script type="text/javascript">
$(document).ready(function() {
    $('#signupForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            full_name: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                enabled: false,
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'confirm_password',
                        message: '两次密码输入不一致'
                    }
                }
            },
            confirm_password: {
                enabled: false,
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不一致'
                    }
                }
            }
        }
    });

    // Enable the password/confirm password validators if the password is not empty
    $('#signupForm').find('[name="password"]').on('keyup', function() {
        var isEmpty = $(this).val() == '';
        $('#signupForm').bootstrapValidator('enableFieldValidators', 'password', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'confirm_password', !isEmpty);
        if ($(this).val().length == 1) {
            $('#signupForm').bootstrapValidator('validateField', 'password')
                            .bootstrapValidator('validateField', 'confirm_password');
        }
    });
});
</script>
</body>
</html>