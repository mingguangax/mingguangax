<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算单明细管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="consumption"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>结算单明细</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单明细</h3>
                    </div>
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <div class="btn-group">
                                <#-- 新增 -->
                                <button type="button" class="btn btn-default btn-sm checkbox-toggle btn-input"><i class="fa fa-plus"></i></button>
                                <#-- 删除 -->
                                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
                            </div>
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox"></th>
                                        <th>业务大类</th>
                                        <th>业务小类</th>
                                        <th>结算类型</th>
                                        <th>消费金额(元)</th>
                                        <th>优惠金额(元)</th>
                                        <th>实收金额(元)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list consumptionItems as consumption>
                                        <tr>
                                            <td><input type="checkbox"></td>
                                            <td>${consumption.category.name}</td>
                                            <td>${consumption.categoryItem.name}</td>
                                            <td>${consumption.payType.name}</td>
                                            <td>${consumption.amount?c}</td>
                                            <td>${consumption.discountAmount?c}</td>
                                            <td>${consumption.payAmount?c}</td>
                                        </tr>
                                    </#list>

                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th>总消费金额:${consumption.totalAmount}元</th>
                                        <th>总优惠金额:${consumption.discountAmount}元</th>
                                        <th>总实收金额:${consumption.payAmount}元</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <br/>
				    <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单信息</h3>
                    </div>
                    <form class="box-body" class="form-horizontal" id="editForm" action="/consumption/saveOrUpdate" method="post" >
                        <input type="hidden" name="id" value="${consumption.id}" >
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>客户名称：</label>
                                    <input type="text" name="customerName" value="${consumption.customerName}" class="form-control" placeholder="请输入客户名称">
                                </div>
                                <div class="form-group">
                                    <label>客户电话：</label>
                                    <input type="text" class="form-control" name="customerTel" value="${consumption.customerTel}" placeholder="请输入客户电话">
                                </div>
                                <div class="form-group">
                                    <label>消费门店：</label>
                                    <select class="form-control" name="business.id">
                                        <option value="">请选择门店</option>
                                        <#list businesses as b>
                                            <option value="${b.id}">${b.name}</option>
                                        </#list>
                                    </select>
                                    <script>
                                        $("select[name='business.id']").val(${consumption.business.id})
                                    </script>
                                </div>
                                <div class="form-group">
                                    <label>进店时间：</label>
                                    <input type="text" class="form-control" name="checkinTime" value="${(consumption.checkinTime?string('yyyy-MM-dd HH:mm'))!}">
                                </div>
                                <div class="form-group">
                                    <label>离店时间：</label>
                                    <input type="text" class="form-control" name="checkoutTime" value="${(consumption.checkoutTime?string('yyyy-MM-dd HH:mm'))!}">
                                </div>
                                <div class="form-group">
                                    <label>车牌记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车牌记录" name="carLicence" value="${consumption.carLicence}">
                                </div>
                                <div class="form-group">
                                    <label>车型记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车型记录" name="carType" value="${consumption.carType}">
                                </div>

                                <div class="form-group">
                                    <label>结算单备注：</label>
                                    <textarea class="form-control"  rows="4" name="info"
                                              placeholder="请输入结算单备注">${consumption.info}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>结算单状态：</label>
                                    <input type="text" class="form-control"  readonly value="${consumption.statusName}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>总消费金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入总消费金额" name="totalAmount" value="${(consumption.totalAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>优惠金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入优惠金额" name="discountAmount" value="${(consumption.discountAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>实收金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入实收金额" name="payAmount" value="${(consumption.payAmount?c)!}">
                                </div>
                                    <div class="form-group">
                                        <label>结算单流水号：</label>
                                        <input type="text" class="form-control" readonly  value="${consumption.cno}">
                                    </div>
                                    <div class="form-group">
                                        <label>关联预约单流水号：</label>
                                        <input type="text" class="form-control" readonly  value="${consumption.appointmentAno}">
                                    </div>
                                    <div class="form-group">
                                        <label>创建时间：</label>
                                        <input type="text" class="form-control" readonly  value="${(consumption.createTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                    </div>
                                    <div class="form-group">
                                        <label>结算时间：</label>
                                        <input type="text" readonly class="form-control" readonly  value="${(consumption.payTime?string('yyyy-MM-dd HH:mm:ss'))!}"/>
                                    </div>
                                    <div class="form-group">
                                        <label>结算人：</label>
                                        <input type="text" class="form-control" readonly  value="${consumption.payee.name}">
                                    </div>
                                    <div class="form-group">
                                        <label>审核时间：</label>
                                        <input type="text" class="form-control" readonly  value="${(consumption.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                    </div>
                                    <div class="form-group">
                                        <label>审核人：</label>
                                        <input type="text" class="form-control" readonly  value="${consumption.auditor.name}">
                                    </div>
                            </div>
                        </div>

                        <div class="pull-right">
                            <button type="submit" class="btn btn-primary btn-submit"><span class="glyphicon glyphicon-book"></span> 保存</button>
                            <button type="button" class="btn btn-warning btn-consume"><span class="glyphicon glyphicon-yen"></span> 结算</button>
                            <button type="button" class="btn btn-success btn-audit"><span class="glyphicon glyphicon-flag"></span> 审核</button>
							<button type="button" class="btn btn-danger btn-delete"><span class="glyphicon glyphicon-trash"></span> 删除</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">后退</button>
                        </div>

                    </form>
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>

<script>
    $(function () {
        $('.btn-input').click(function () {
            $('#editModal').modal('show')
        })

    })
</script>
<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增结算明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="consumptionItemForm" action="/consumptionItem/saveOrUpdate" method="post" >
                <#-- 结算单流水号 -->
                <input type="hidden" name="cno" value="${consumption.cno}">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="category" name="category.id">
                                <option value="">请选择业务大类</option>
                                <#list categories as category>
                                    <option value="${category.id}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务小类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="categoryItem" name="categoryItem.id">
                                <option value="">请选择业务小类</option>
                            </select>
                        </div>
                    </div>
                    <script>
                        $(function () {
                            $('#category').change(function () {
                                var id = $(this).val();
                                var $select = $('#categoryItem');
                                //清除数据
                                $select.empty();
                                $select.append('<option value="">请选择业务小类</option>');

                                $.get('/systemDictionary/queryItemById','id='+id,function (data) {
                                    data.forEach(function (ele) {
                                        $select.append('<option value="'+ele.id+'">'+ele.name+'</optiong>')
                                    })
                                })
                            })
                        })
                    </script>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">结算类型：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="payType" name="payType.id">
                                <option value="">请选择结算类型</option>
                                <#list payType as payType>
                                    <option value="${payType.id}">${payType.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">消费金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="amount"
                                   placeholder="请输入应收金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">优惠金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="discountAmount"
                                      placeholder="请输入优惠金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">实收金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="payAmount"
                                   placeholder="请输入实收金额">
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


</body>
</html>
