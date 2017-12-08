package com.kingbbode.generator;

import com.kingbbode.adapters.DynamicBeanGeneratorAdapter;
import com.kingbbode.bean.ExecuteExceptionBean;
import com.kingbbode.interfaces.Executable;

/**
 * Created by YG on 2017-01-11.
 */
public class TestExecuteExceptionBeanDynamicGenerator extends DynamicBeanGeneratorAdapter<ExecuteExceptionBean> implements Executable {
    
    @Override
    public ExecuteExceptionBean generate() throws Exception {
        return new ExecuteExceptionBean();
    }

    @Override
    public void execute() throws Exception {
        throw new Exception();
    }

    @Override
    public boolean isOnlyDirectGenerate() {
        return true;
    }
}
