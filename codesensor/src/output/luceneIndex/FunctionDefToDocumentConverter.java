package output.luceneIndex;


import lucene.LuceneUtils;

import org.apache.lucene.document.Document;

import output.csvast.ASTToCSVConverter;

import astnodes.functionDef.FunctionDef;

public class FunctionDefToDocumentConverter
{
	public static void convert(FunctionDef item, String filename, Document d)
	{			
		CommonCodeItemToDocument.addStandardFields(item, filename, d);
		d.add(LuceneUtils.createField("name", item.name.getCodeStr()));
		d.add(LuceneUtils.createField("returnType", item.returnType.getCodeStr()));
		addParameters(item, d);		
		addAst(item, d);
	}

	private static void addAst(FunctionDef node, Document d)
	{
		ASTToCSVConverter astToCSV = new ASTToCSVConverter();
		node.accept(astToCSV);
		d.add(LuceneUtils.createUnindexField("ast", astToCSV.getResult()));
	}

	private static void addParameters(FunctionDef item, Document d)
	{
		
		String[] names = item.parameterList.getNameStrings();
		for(String paramName : names){
			d.add(LuceneUtils.createField("parameterName", paramName));
		}
		
		String[] types = item.parameterList.getTypeStrings();
		for(String paramType : types){
			d.add(LuceneUtils.createField("parameterType", paramType));
		}	
	}	


}
