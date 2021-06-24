<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典目录管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">

    <#assign currentMenu="systemDictionary"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>数据字典管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <button type="button" class="btn btn-primary btn-save"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加根级数据字典</button>
                <#macro tree ma>
                    <#list ma as systemDictionary>
                        <li class="treeview">
                            <a href="#">
                                <span>${systemDictionary.name}</span>
                                <span style="float: right; margin-right: 40px;">
                                     <button ='${systemDictionary.toJson()}' class="btn btn-primary btn-xs btn-save">添加</button>
                                     <button data-json='${systemDictionary.toJson()}' class="btn btn-primary btn-xs btn-update">修改</button>
                                     <button data-id='${systemDictionary.id}' class="btn btn-danger btn-xs btn-delete">删除</button>
                                </span>
                                <#-- 有儿子, 则有箭头 -->
                                <#if systemDictionary.items?size gt 0 >
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </#if>
                            </a>
                            <#-- 代表有儿子 再调用自己, 把儿子做为参数 -->
                            <#if systemDictionary.items?size gt 0 >
                                <ul class="treeview-menu">
                                   <@tree ma=systemDictionary.items></@tree>
                                </ul>
                            </#if>
                        </li>
                    </#list>
                </#macro>

                <#-- 调用我们自定义指令, 传递参数, 参数就是我们从数据库中查询出来所有数据字典 -->
                <ul class="sidebar-menu" data-widget="tree">
                    <@tree ma=systemDictionaries></@tree>
                </ul>
            </div>
        </section>
    </div>
    <script>
        $(function () {
            $('.btn-delete').click(function () {
                // 拿到被删除按钮, 获取上面 id 值
                var id = $(this).data('id');
                Swal.fire({
                    title: '您确定要删除吗？',
                    text: "若此数据下还有子数据也会一并删除!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then((result) => {
                    if(result.value) {
                        location.href = '/systemDictionary/delete?id=' + id;
                    }
                });
            });

            $('.btn-save').click(function () {

                // 清除模态框中的数据
                $('#saveModal input').val('');

                var data = $(this).data('json');
                if(data) { // 若是添加非根数据字典
                    // 把对应数据字典的 id 和 name 设置模态框中 option 元素中
                    $('select[name=parentId] > option').val(data.id)
                        .html(data.name);
                } else { // 若是添加根数据字典, 则模态框中 option 没有值
                    $('select[name=parentId] > option').val('')
                        .html('');
                }
                $('#saveModal').modal('show');
                return false; // 阻止事件传递
            });

            $('.btn-update').click(function () {
                var data = $(this).data('json');
                if(data) {
                    $('#updateModal input[name=id]').val(data.id);
                    $('#updateModal input[name=name]').val(data.name);
                    $('#updateModal input[name=sn]').val(data.sn);
                    $('#updateModal input[name=intro]').val(data.intro);
                }

                $('#updateModal').modal('show');
                return false; // 阻止事件传递
            });
        });
    </script>
    <#include "/common/footer.ftl" >
</div>


<!-- Modal -->
<div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="mySaveModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="mySaveModalLabel">新增</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" placeholder="请输入名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="sn" class="form-control" placeholder="请输入编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="intro" class="col-sm-3 control-label">简介：</label>
                        <div class="col-sm-6">
                            <input type="text" name="intro" class="form-control" placeholder="请输入介绍">
                        </div>
                    </div>
                    <div id="parentDiv" class="form-group" style="margin-top: 10px;">
                        <label for="parentId" class="col-sm-3 control-label">父级：</label>
                        <div class="col-sm-6">
                            <select name="parentId" readonly="" class="form-control">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myUpdateModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myUpdateModalLabel">编辑</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" placeholder="请输入名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="sn" class="form-control" placeholder="请输入编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="intro" class="col-sm-3 control-label">简介：</label>
                        <div class="col-sm-6">
                            <input type="text" name="intro" class="form-control" placeholder="请输入介绍">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
