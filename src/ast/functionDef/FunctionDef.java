package ast.functionDef;

import ast.ASTNode;
import ast.ASTNodeProperties;
import ast.expressions.Identifier;
import ast.logical.statements.CompoundStatement;
import ast.walking.ASTNodeVisitor;

public class FunctionDef extends ASTNode
{

	protected Identifier identifier = null;
	protected ParameterList parameterList = null;
	protected CompoundStatement content = null;
	protected Identifier returnType = null;

	public void addChild(ASTNode node)
	{
		if (node instanceof CompoundStatement)
			setContent((CompoundStatement) node);
		else if (node instanceof ParameterList)
			setParameterList((ParameterList) node);
		else if (node instanceof Identifier)
			setIdentifier((Identifier) node);
		else
			super.addChild(node);
	}
	
	public String getName() {
		return getProperty(ASTNodeProperties.NAME);
	}
	
	public void setName(String name) {
		setProperty(ASTNodeProperties.NAME, name);
	}
	
	public String getDocComment() {
		return getProperty(ASTNodeProperties.DOCCOMMENT);
	}
	
	public void setDocComment(String doccomment) {
		setProperty(ASTNodeProperties.DOCCOMMENT, doccomment);
	}

	public CompoundStatement getContent()
	{
		return this.content;
	}
	
	public void setContent(CompoundStatement content)
	{
		this.content = content;
		super.addChild(content);
	}
	
	public Identifier getReturnType()
	{
		return this.returnType;
	}
	
	public void setReturnType(Identifier returnType)
	{
		this.returnType = returnType;
		super.addChild(returnType);
	}

	@Override
	public String getEscapedCodeStr()
	{
		setCodeStr(getFunctionSignature());
		return getCodeStr();
	}

	public String getFunctionSignature()
	{
		String retval = getIdentifier().getEscapedCodeStr();
		if (getParameterList() != null)
			retval += " (" + getParameterList().getEscapedCodeStr() + ")";
		else
			retval += " ()";
		return retval;
	}

	public void accept(ASTNodeVisitor visitor)
	{
		visitor.visit(this);
	}

	public ParameterList getParameterList()
	{
		return this.parameterList;
	}

	public void setParameterList(ParameterList parameterList)
	{
		this.parameterList = parameterList;
		super.addChild(parameterList);
	}

	public Identifier getIdentifier()
	{
		return this.identifier;
	}

	private void setIdentifier(Identifier identifier)
	{
		this.identifier = identifier;
		super.addChild(identifier);
	}

}
