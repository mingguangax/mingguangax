$(function () {
    $('.btn-delete').click(function () {
        var url = $(this).data('url');
        Swal.fire({
            title: '您确定要删除吗?',
            text: '此操作不可撤销!',
            icon: 'warning?',
            showCancelButton: true,
            confirmButtonColor: '#30856d?',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定',
            cancelButtonText: '取消'
        }).then((result)=>{
            if (result.value) {
                location.href=url;
            }
        });
    });
    $('.input-date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minView : 0,
        todayHighlight: true, // 今天时间高亮
        format: 'yyyy-mm-dd hh:ii:ss',
        todayBtn: true
    });
});
