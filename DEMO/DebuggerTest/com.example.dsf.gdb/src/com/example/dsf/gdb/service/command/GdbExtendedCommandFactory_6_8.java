package com.example.dsf.gdb.service.command;

import org.eclipse.cdt.dsf.debug.service.IBreakpoints.IBreakpointsTargetDMContext;
import org.eclipse.cdt.dsf.debug.service.command.ICommand;
import org.eclipse.cdt.dsf.debug.service.command.ICommandControlService.ICommandControlDMContext;
import org.eclipse.cdt.dsf.gdb.service.command.CommandFactory_6_8;
import org.eclipse.cdt.dsf.mi.service.command.output.MIBreakInsertInfo;
import org.eclipse.cdt.dsf.mi.service.command.output.MIGDBVersionInfo;

import com.example.dsf.gdb.service.command.commands.CLIGDBVersion;

public class GdbExtendedCommandFactory_6_8 extends CommandFactory_6_8 {
	@Override
	public ICommand<MIBreakInsertInfo> createMIDPrintfInsert(
			IBreakpointsTargetDMContext ctx, boolean isTemporary,
			String condition, int ignoreCount, int tid, boolean disabled,
			String location, String printfStr) {
		// Prefix all dynamic printf with the [EX] tag
		printfStr = printfStr.replaceFirst("^\"", "\"[EX] "); //$NON-NLS-1$ //$NON-NLS-2$
		return super.createMIDPrintfInsert(ctx, isTemporary, condition, ignoreCount,
				tid, disabled, location, printfStr);
	}
	
	public ICommand<MIGDBVersionInfo> createCLIGDBVersion(ICommandControlDMContext ctx) {
		return new CLIGDBVersion(ctx);
	}
}