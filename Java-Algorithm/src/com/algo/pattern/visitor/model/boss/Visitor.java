package com.algo.pattern.visitor.model.boss;

import com.algo.pattern.visitor.model.staff.Engineer;
import com.algo.pattern.visitor.model.staff.Manager;

public interface Visitor {

    // ���ʹ���ʦ����
    void visit(Engineer engineer);

    // ���ʾ�������
    void visit(Manager manager);
}