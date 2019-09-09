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
            <section>
                <div class="col-sm-8 col-sm-offset-2">
                    <div class="page-header">
                        <h2>深圳考试管理平台111</h2>
                    </div>

                    <form id="defaultForm" method="post" class="form-horizontal" action="target.php" style="margin-top: 200px">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="firstName" placeholder="请输入用户名" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="lastName" placeholder="请输入用密码" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-9 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">登录</button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>

<script type="text/javascript">
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        container: 'tooltip',
//        trigger: 'blur',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                validators: {
            /*         stringLength: {
                        enabled: false,
                        min: 0,
                        message: '用户名不能为空'
                    }, */
                    notEmpty: {
                        message: '用户名必须填写'
                    },
             /*        regexp: {
                        enabled: true,
                        regexp: /^[a-z]+$/i,
                        message: 'The first name must consist of a-z, A-Z characters only'
                    } */
                }
            },
            lastName: {
                validators: {
/*                     stringLength: {
                        min: 4,
                        message: 'The last name must be more than 5 characters'
                    }, */
                    notEmpty: {
                        message: '密码不能为空'
                    },
/*                     regexp: {
                        regexp: /^[a-z]+$/i,
                        message: 'The last name must consist of a-z, A-Z characters only'
                    } */
                }
            }
        }
    });
});
</script>
</body>
</html>