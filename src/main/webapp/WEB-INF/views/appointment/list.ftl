<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预约单管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="appointment"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>预约单管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/appointment/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label>预约单流水号</label>
                            <input type="text" class="form-control" name="ano" placeholder="请输入预约单流水号" value="${qo.ano}">
                        </div>
                                <div class="form-group">
                                    <label>预约业务</label>
                                    <select class="form-control" id="categoryId" name="systemDictionaryId">
                                        <option value="">请选择业务</option>
                                        <#list systemDictionaries as systemDictionary>
                                            <option value="${systemDictionary.id}"
                                                    <#if qo.systemDictionaryId==systemDictionary.id>selected</#if>
                                            >${systemDictionary.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>预约单状态</label>
                                    <select class="form-control" id="status" name="status" >
                                        <option value="">全部</option>
                                        <#list appointmentStatusEnums as appointmentStatusEnum>
                                            <option value="${appointmentStatusEnum.getStatus()}"
                                                    <#if qo.status==appointmentStatusEnum.getStatus()>selected</#if>
                                            >${appointmentStatusEnum.getName()}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>门店查询</label>
                                    <select class="form-control"  id="businessId" name="businessId">
                                        <option value="">请选择门店</option>
                                        <#list business as business>
                                            <option value="${business.id}"
                                                    <#if qo.businessId==business.id>selected</#if>
                                            >${business.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>客户名称</label>
                                    <input type="text" class="form-control" name="concatName" placeholder="请输入客户名称" value="${qo.concatName}">
                                </div>

                                <div class="form-group">
                                    <label>客户手机号</label>
                                    <input type="text" class="form-control" name="concatTel" placeholder="请输入客户手机号"value="${qo.concatTel}">
                                </div>

                                <br/>
                                <br/>

                        <div class="form-group">
                            <label>预约时间查询：</label>
                            <input placeholder="请输入开始时间" type="text" name="beginAppointmentTime"
                                   value="${(qo.beginAppointmentTime?string('yyyy-MM-dd'))!}"
                                   class="form-control input-date" /> -
                            <input placeholder="请输入结束时间" type="text" name="endAppointmentTime"
                                   value="${(qo.endAppointmentTime?string('yyyy-MM-dd'))!}"
                                   class="form-control input-date" />
                        </div>
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>

                        <a href="#" class="btn btn-success btn-input">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                    </form>
<script>
    $(function () {
        $('.btn-input').click(function () {
            //若是修改才回显数据
            var data = $(this).data('json');
            //清除模态框中的数据
            $('#editForm input').val('');
            $('#editForm select').val('');
            $('#editForm textarea').val('');
            if (data) {
                $('input[name=id]').val(data.id);
                $('select[name="business.id"]').val(data.businessId);
                $('input[name=appointmentTime]').val(data.appointmentTime);
                $('select[name="systemDictionary.id"]').val(data.systemDictionaryId);
                $('input[name=contactName]').val(data.contactName);
                $('input[name=contactTel]').val(data.contactTel);
                $('textarea[name=info]').val(data.info)
            }
            $('#editModal').modal('show')
        })

    })
</script>

                </div>
                <div class="box-body table-responsive ">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>流水号</th>
                        <th>业务分类</th>
                        <th>预约说明</th>
                        <th>预约时间</th>
                        <th>客户名称</th>
                        <th>联系方式</th>
                        <th>预约门店</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pageInfo.list as appointment>
                        <tr>
                            <td>${appointment.id}</td>
                            <td>${appointment.ano}</td>
                            <td>${appointment.systemDictionary.name}</td>
                            <td>${appointment.info}</td>
                            <td>${appointment.appointmentTime?string('yyyy-MM-dd')}</td>
                            <td>${appointment.contactName}</td>
                            <td>${appointment.contactTel}</td>
                            <td>${appointment.business.name}</td>
                            <td>${appointment.statusName}</td>
                            <td>
                                <a href="#" class="btn btn-info btn-xs btn-input" data-json='${appointment.toJson()}'>
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                                <a class="btn btn-xs btn-primary btn-status" data-id="${appointment.id}" data-status="1" >
                                    <span class="glyphicon glyphicon-phone-alt"></span> 确认预约</a>
                                <a class="btn btn-xs btn-danger btn-status" data-id="${appointment.id}" data-status="4">
                                    <span class="glyphicon glyphicon-remove"></span> 取消预约</a>
                                <a href="#" class="btn btn-success btn-xs btn-consume" data-id="${appointment.id}" >
                                    <span class="glyphicon glyphicon-shopping-cart"></span> 确认到店
                                </a>

                                <script>
                                    $(function () {
                                        $('.btn-status').click(function () {
                                            var status = $(this).data('status');
                                            var tip = status ==1 ? '用户真的确认预约吗':'用户真的想取消预约吗';
                                            var id = $(this).data('id');
                                            //msg提示用户是确认预约 还是取消预约
                                            Swal.fire({
                                                text: tip,
                                                icon: 'warning?',
                                                showCancelButton: true,
                                                confirmButtonColor: '#30856d?',
                                                cancelButtonColor: '#d33',
                                                confirmButtonText: '确定',
                                                cancelButtonText: '取消'
                                            }).then((result)=>{
                                                if (result.value) {
                                                    //修改预约单
                                                    location.href= '/appointment/updateStatus?id='+id+'&status='+status;
                                                }
                                            });
                                        })
                                    })

                                </script>
                                <script>
                                    $('.btn-consume').click(function () {
                                        var id = $(this).data('id');
                                        console.log(id);
                                        Swal.fire({
                                            title: '您确定要消费吗?',
                                            icon: 'warning?',
                                            showCancelButton: true,
                                            confirmButtonColor: '#30856d?',
                                            cancelButtonColor: '#d33',
                                            confirmButtonText: '确认消费',
                                            cancelButtonText: '取消消费'
                                        }).then((result)=>{
                                            if (result.value) {
                                                //有消费,预约改成消费中 再添加一条数据
                                                location.href= '/consumption/save?appointmentId='+id;
                                            }else {
                                                //跳转归档
                                                location.href= '/appointment/updateStatus?id='+id+'&status=3';
                                            }
                                        });
                                    });
                                </script>

                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                    <!--分页-->
                    <#include "/common/page.ftl">
                </div>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>


<#-- 文件上传模态框 -->
<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="editForm" action="/appointment/saveOrUpdate" method="post" >
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <input type="hidden" name="status" value="1">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" >预约门店：</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="business.id" >
                                <option value="">请选择预约门店</option>
                                <#list business as bus>
                                    <option value="${bus.id}">${bus.name}
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约时间：</label>
                        <div class="col-sm-7">
                            <input type="text" name="appointmentTime" class="form-control input-date" placeholder="请输入预约时间"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control category" name="systemDictionary.id" >
                                <option value="">请选择预约大类</option>
                                <#list systemDictionaries as sys>
                                    <option value="${sys.id}">${sys.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系人：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactName"
                                   placeholder="请输入联系人">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系电话：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactTel"
                                   placeholder="请输入联系电话">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约说明：</label>
                        <div class="col-sm-7">
                            <textarea type="text" class="form-control" name="info"
                                      placeholder="请输入预约说明"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('.input-data').datepicker({
            language: 'zh-CN',
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd',
            startDate: new Date()
        });
    });
</script>

</body>
</html>
