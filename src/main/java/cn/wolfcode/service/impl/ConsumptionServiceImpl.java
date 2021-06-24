package cn.wolfcode.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.wolfcode.domain.Appointment;
import cn.wolfcode.domain.Consumption;
import cn.wolfcode.enums.AppointmentStatusEnum;
import cn.wolfcode.mapper.AppointmentMapper;
import cn.wolfcode.mapper.ConsumptionMapper;
import cn.wolfcode.qo.ConsumptionQueryObject;
import cn.wolfcode.service.IConsumptionService;
import cn.wolfcode.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {


    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Override
    public void insert(Consumption consumption) {
        consumptionMapper.insert(consumption);
    }

    @Override
    public void deleteById(Long id) {
        consumptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Consumption consumption) {
        consumptionMapper.updateByPrimaryKey(consumption);
    }

    @Override
    public Consumption selectById(Long id) {
        Consumption consumption = consumptionMapper.selectByPrimaryKey(id);
        return consumption;
    }

    @Override
    public List<Consumption> selectAll() {
        List<Consumption> consumptions = consumptionMapper.selectAll();
        return consumptions;
    }

    @Override
    public PageInfo<Consumption> selectByList(ConsumptionQueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"c.status,c.create_time");

        List<Consumption> consumptions =consumptionMapper.selectForList(qo);
        return new PageInfo<>(consumptions);
    }

    @Override
    public Consumption save(Long appointmentId) {
        //把对应预约单该程序消费单 再添加一条数据
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
        appointment.setStatus(AppointmentStatusEnum.CONSUME.getStatus());
        appointmentMapper.updateStatus(appointment);
        //新增一条
        Consumption consumption = new Consumption();
        Date now = new Date();
        consumption.setCreateTime(now);
        consumption.setCheckinTime(now);
        consumption.setCreateUser(UserContext.getEmployee());
        consumption.setCustomerName(appointment.getContactName());
        consumption.setCustomerTel(appointment.getContactTel());
        consumption.setBusiness(appointment.getBusiness());
        consumption.setAppointmentAno(appointment.getAno());

        consumption.setCno(DateUtil.format(now,"yyyyMMddHHmmss")+ RandomUtil.randomNumbers(5));

        consumptionMapper.insert(consumption);
        return consumption;
    }
}













