

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="role"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate" method="post" id="editForm">

                    <input type="hidden" value="${role.id}" name="id">
                    <div class="form-group"  style="margin-top: 10px;">
                        <label  class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="${role.name}" name="name"  placeholder="请输入角色名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="${role.sn}" name="sn"  placeholder="请输入角色编号">
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" style="height: 350px;" size="15">
                                    <#list allpermission as permission>
                                        <option value="${permission.id}">${permission.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <script>
                                function moveAll(srcClass,targetClass) {
                                    $('.'+targetClass).append($('.'+srcClass+'>option'))
                                }
                                function moveSelected(srcClass,targetClass) {
                                    $('.'+targetClass).append($('.'+srcClass+'>option:selected'))
                                }
                            </script>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" style="height: 350px;" size="15" name="permissionIds">
                                    <#list permissions as permission>
                                        <option value="${permission.id}">${permission.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-primary btn-submit">保存</button>
                            <a href="javascript:window.history.back()" class="btn btn-danger">取消</a>
                        </div>
                    </div>
<script>
    $('.btn-submit').click(function () {
        //让右边所有的Option都选中
        $('.selfPermissions>option').prop('selected',true);
        //提交表单
        $('#editForm').submit();
    })
</script>

                </form>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
</body>
</html>
