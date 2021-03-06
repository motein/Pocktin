package com.example.dsf.gdb.viewmodel;

import org.eclipse.cdt.dsf.concurrent.ThreadSafe;
import org.eclipse.cdt.dsf.debug.ui.viewmodel.SteppingController;
import org.eclipse.cdt.dsf.gdb.internal.ui.viewmodel.GdbViewModelAdapter;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.cdt.dsf.ui.viewmodel.IVMProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.ui.IDebugUIConstants;

@SuppressWarnings("restriction")
@ThreadSafe
public class GdbExtendedViewModelAdapter extends GdbViewModelAdapter
{
    public GdbExtendedViewModelAdapter(DsfSession session, SteppingController controller) {
        super(session, controller);
    }    

    @Override
    public void dispose() {
        super.dispose();
    }
    
    @Override
    protected IVMProvider createViewModelProvider(IPresentationContext context) {
        if (IDebugUIConstants.ID_DEBUG_VIEW.equals(context.getId())) {
            return new GdbExtendedLaunchVMProvider(this, context, getSession()); 
        } else {
        	return super.createViewModelProvider(context);
        }
    }    
}