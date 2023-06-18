package com.algo.pattern.visitor.model.boss;

import com.algo.pattern.visitor.model.staff.Engineer;
import com.algo.pattern.visitor.model.staff.Manager;

public interface Visitor {

    // 访问工程师类型
    void visit(Engineer engineer);

    // 访问经理类型
    void visit(Manager manager);
}