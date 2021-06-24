package cn.wolfcode.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.wolfcode.domain.Appointment;
import cn.wolfcode.mapper.AppointmentMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IAppointmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public void insert(Appointment appointment) {
        appointmentMapper.insert(appointment);
    }

    @Override
    public void deleteById(Long id) {
        appointmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentMapper.updateByPrimaryKey(appointment);
    }

    @Override
    public Appointment selectById(Long id) {
        Appointment appointment = appointmentMapper.selectByPrimaryKey(id);
        return appointment;
    }

    @Override
    public List<Appointment> selectAll() {
        List<Appointment> appointments = appointmentMapper.selectAll();
        return appointments;
    }

    @Override
    public PageInfo<Appointment> selectByList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"a.status,a.appointment_time");
        List<Appointment> appointments =appointmentMapper.selectForList(qo);
        return new PageInfo<>(appointments);
    }

    @Override
    public void save(Appointment appointment) {
        //生成预约单号
        //生成五位数
        //生成时间
        Date date = new Date();
        appointment.setAno(DateUtil.format(date,"yyyyMMddHHmmss")+RandomUtil.randomNumbers(5));
        appointment.setCreateTime(date);
        appointmentMapper.insert(appointment);
    }

    @Override
    public void updateStatus(Appointment appointment) {
        appointmentMapper.updateStatus(appointment);
    }
}
