package EnemyLevelScaling.GeneticProgrammer.Mk5.Enums;

public enum NodeType
{
	IfElse(ReturnType.Void, false),
	While(ReturnType.Void, false),
	IntGetter(ReturnType.Integer, false),
	IntSetter(ReturnType.Void, false),
	DubGetter(ReturnType.Double, false),
	DubSetter(ReturnType.Void, false),
	GetVar(ReturnType.Double, true),
	ConstInt(ReturnType.Integer, true),
	ConstDub(ReturnType.Double, true),
	ConstBool(ReturnType.Boolean, true),
	ConstVoid(ReturnType.Void, true),
	BinaryNumNum(ReturnType.Double, false),
	BinaryNumBool(ReturnType.Boolean, false),
	BinaryBoolBool(ReturnType.Boolean, false),
	UnaryNum(ReturnType.Double, false),
	UnaryBool(ReturnType.Boolean, false),
	;
	
	private static NodeType[] All = values();
	
	private ReturnType Returns;
	private boolean isTerminal;
	
	private NodeType(ReturnType inReturns, boolean inIsTerminal)
	{
		this.Returns = inReturns;
		this.isTerminal = inIsTerminal;
	}
	
	public ReturnType getReturnType()
	{
		return this.Returns;
	}
	
	public boolean isTerminal()
	{
		return this.isTerminal;
	}
	
	public static NodeType getEnumAtIndex(int in)
	{
		return All[in];
	}
	
	public static int getNumValues()
	{
		return All.length;
	}
}
