//package Java.AST.visitor_java;
//
//import Java.AST.FunctionDeclaration;
//import Java.AST.function.function_header;
//import generated.SQLBaseVisitor;
//import generated.SQLParser;
//
//public class visitefunction extends SQLBaseVisitor {
//    public FunctionDeclaration  visitFuntion(SQLParser.FuntionContext ctx) {
//        System.out.println("function declaration ");
//        FunctionDeclaration funcdec = new FunctionDeclaration();
//        funcdec.setHeader(visitFunction_header(ctx.function_header()));
//        return funcdec;
//        // we should also visite function body also
//    }
//    public function_header visitFunction_header(SQLParser.Function_headerContext ctx) {
//        System.out.println("function_header ");
//        function_header header = new function_header();
//     header.setName(visitUse_random_name(ctx.use_random_name()));
//     // set list of parameteres
//        return header ;
//
//    }
//    public String  visitUse_random_name(SQLParser.Use_random_nameContext ctx) {
//        System.out.println("visite randomname");
//        String name ="";
//        if (ctx.RANDOM_NAME()!=null)
//        {
//            name = ctx.RANDOM_NAME().getSymbol().getText();
//
//        }
//
//        return name ;
//
//    }
//}
