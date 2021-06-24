<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm" >
                    <input type="hidden" value="${employee.id}" name="id" >
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" ${(employee.id!=null)?string('disabled','')} value="${employee.username}" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">真实姓名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" ${(employee.id!=null)?string('disabled','')} value="${employee.name}" placeholder="请输入真实姓名">
                        </div>
                    </div>
                    <#if !employee??>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label" >密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" value="${employee.password}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="repassword" name="repassword" placeholder="再输入一遍密码" value="${employee.password}">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label" >电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="${employee.email}" name="email" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label" >年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="${employee.age}" name="age" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept_id">
                                <option value="">全部</option>
                                <#list department as department>
                                    <option value="${department.id}" ${(employee.dept_id==department.id)?string('selected','')}  >${department.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6"style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox" ${((employee.admin)!false)?string('checked','')}>
                        </div>
                    </div>
                    <div class="form-group" id="roleDiv">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" style="height: 350px;" size="15">
                                    <#list role as role>
                                        <option value="${role.id}">${role.name}</option>
                                    </#list>

                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" style="height: 350px;" size="15" name="roleId">
                                    <#list rightRole as role>
                                        <option value="${role.id}">${role.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <script>
                        $(function () {
                            $("#editForm").bootstrapValidator({
                                feedbackIcons: { // 图标
                                    valid: 'glyphicon glyphicon-ok',
                                    invalid: 'glyphicon glyphicon-remove',
                                    validating: 'glyphicon glyphicon-refresh'
                                },
                                fields:{ // 配置要验证的字段
                                    username:{
                                        validators:{ // 验证的规则
                                            notEmpty:{ // 不能为空
                                                message:"用户名必填" // 错误时的提示信息
                                            },
                                            stringLength: { // 字符串的长度范围
                                                min: 1,
                                                max: 5
                                            },
                                            remote: {
                                                type: 'POST',
                                                url: '/employee/checkUsername',
                                                message: '用户名已占用',
                                                delay: 1000
                                            }
                                        }
                                    },
                                    name:{
                                        validators:{ // 验证的规则
                                            notEmpty:{ // 不能为空
                                                message:"姓名必填" // 错误时的提示信息
                                            },
                                            stringLength: { // 字符串的长度范围
                                                min: 1,
                                                max: 5
                                            }
                                        }
                                    },
                                    password:{
                                        validators:{
                                            notEmpty:{ // 不能为空
                                                message:"密码必填" // 错误时的提示信息
                                            },
                                        }
                                    },
                                    repassword:{
                                        validators:{
                                            notEmpty:{ // 不能为空
                                                message:"密码必填" // 错误时的提示信息
                                            },
                                            identical: {// 两个字段的值必须相同
                                                field: 'password',
                                                message: '两次输入的密码必须相同'
                                            },
                                        }
                                    },
                                    email: {
                                        validators: {
                                            emailAddress: {} // 邮箱格式
                                        }
                                    },
                                    age:{
                                        validators: {
                                            between: { // 数字的范围
                                                min: 18,
                                                max: 60
                                            }
                                        }
                                    }
                                }
                            }).on('success.form.bv', function(e) {
                                //发送异步请求
                                e.preventDefault();
                                var $form = $(e.target);
                                $('.selfRoles > option').prop('selected', 'true');

                                $.post('/employee/saveOrUpdate',$form.serialize()//拿到所有表单的值
                                    ,function (data) {
                                    if (data.success){
                                        location.href = '/employee/list'
                                    }else {
                                        Swal.fire(data.msg)
                                    }
                                })
                            });
                        });
                    </script>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="submit" class="btn btn-primary btn-submit">保存</button>
                            <a href="javascript:window.history.back()" class="btn btn-danger">取消</a>
                        </div>
                    </div>

                </form>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
    <script>
    function moveAll(srcClass,targetClass) {
    $('.'+targetClass).append($('.'+srcClass+'>option'))
    }
    function moveSelected(srcClass,targetClass) {
    $('.'+targetClass).append($('.'+srcClass+'>option:selected'))
    }
    </script>

    <script>
        var $roleDiv;
        $('#admin').click(function () {
            var checked = $(this).prop('checked');
            if (checked){
                $roleDiv = $('#roleDiv').remove();
            }else {
                $('#adminDiv').after($roleDiv);
            }
        });

        $(function () {
            var checked = $('#admin').prop('checked');
            if (checked){
                $roleDiv = $('#roleDiv').remove();
            }
        })
    </script>
</div>
</body>
</html>
