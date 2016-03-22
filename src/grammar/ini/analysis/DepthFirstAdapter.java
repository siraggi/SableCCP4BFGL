/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.analysis;

import java.util.*;
import grammar.ini.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProg().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProg(AProg node)
    {
        defaultIn(node);
    }

    public void outAProg(AProg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProg(AProg node)
    {
        inAProg(node);
        {
            List<PPdcl> copy = new ArrayList<PPdcl>(node.getGlobaldcl());
            for(PPdcl e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getMaindcl() != null)
        {
            node.getMaindcl().apply(this);
        }
        {
            List<PPdcl> copy = new ArrayList<PPdcl>(node.getClassdcl());
            for(PPdcl e : copy)
            {
                e.apply(this);
            }
        }
        outAProg(node);
    }

    public void inAVardclPdcl(AVardclPdcl node)
    {
        defaultIn(node);
    }

    public void outAVardclPdcl(AVardclPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVardclPdcl(AVardclPdcl node)
    {
        inAVardclPdcl(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAVardclPdcl(node);
    }

    public void inAVardclasgPdcl(AVardclasgPdcl node)
    {
        defaultIn(node);
    }

    public void outAVardclasgPdcl(AVardclasgPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVardclasgPdcl(AVardclasgPdcl node)
    {
        inAVardclasgPdcl(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAVardclasgPdcl(node);
    }

    public void inAListdclPdcl(AListdclPdcl node)
    {
        defaultIn(node);
    }

    public void outAListdclPdcl(AListdclPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListdclPdcl(AListdclPdcl node)
    {
        inAListdclPdcl(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAListdclPdcl(node);
    }

    public void inAClassdclPdcl(AClassdclPdcl node)
    {
        defaultIn(node);
    }

    public void outAClassdclPdcl(AClassdclPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClassdclPdcl(AClassdclPdcl node)
    {
        inAClassdclPdcl(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PBody> copy = new ArrayList<PBody>(node.getBody());
            for(PBody e : copy)
            {
                e.apply(this);
            }
        }
        outAClassdclPdcl(node);
    }

    public void inAMaindclPdcl(AMaindclPdcl node)
    {
        defaultIn(node);
    }

    public void outAMaindclPdcl(AMaindclPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMaindclPdcl(AMaindclPdcl node)
    {
        inAMaindclPdcl(node);
        {
            List<PBody> copy = new ArrayList<PBody>(node.getBody());
            for(PBody e : copy)
            {
                e.apply(this);
            }
        }
        outAMaindclPdcl(node);
    }

    public void inAEventdclPdcl(AEventdclPdcl node)
    {
        defaultIn(node);
    }

    public void outAEventdclPdcl(AEventdclPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEventdclPdcl(AEventdclPdcl node)
    {
        inAEventdclPdcl(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParams());
            for(PParam e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getBody());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAEventdclPdcl(node);
    }

    public void inAFuncPdcl(AFuncPdcl node)
    {
        defaultIn(node);
    }

    public void outAFuncPdcl(AFuncPdcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncPdcl(AFuncPdcl node)
    {
        inAFuncPdcl(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParams());
            for(PParam e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        outAFuncPdcl(node);
    }

    public void inAFuncBody(AFuncBody node)
    {
        defaultIn(node);
    }

    public void outAFuncBody(AFuncBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncBody(AFuncBody node)
    {
        inAFuncBody(node);
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        outAFuncBody(node);
    }

    public void inAClassBody(AClassBody node)
    {
        defaultIn(node);
    }

    public void outAClassBody(AClassBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClassBody(AClassBody node)
    {
        inAClassBody(node);
        if(node.getPdcl() != null)
        {
            node.getPdcl().apply(this);
        }
        outAClassBody(node);
    }

    public void inAReturnidReturn(AReturnidReturn node)
    {
        defaultIn(node);
    }

    public void outAReturnidReturn(AReturnidReturn node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAReturnidReturn(AReturnidReturn node)
    {
        inAReturnidReturn(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAReturnidReturn(node);
    }

    public void inAEmptyReturn(AEmptyReturn node)
    {
        defaultIn(node);
    }

    public void outAEmptyReturn(AEmptyReturn node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyReturn(AEmptyReturn node)
    {
        inAEmptyReturn(node);
        outAEmptyReturn(node);
    }

    public void inAFormalParam(AFormalParam node)
    {
        defaultIn(node);
    }

    public void outAFormalParam(AFormalParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFormalParam(AFormalParam node)
    {
        inAFormalParam(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            for(PParam e : copy)
            {
                e.apply(this);
            }
        }
        outAFormalParam(node);
    }

    public void inAInherit(AInherit node)
    {
        defaultIn(node);
    }

    public void outAInherit(AInherit node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInherit(AInherit node)
    {
        inAInherit(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outAInherit(node);
    }

    public void inANumType(ANumType node)
    {
        defaultIn(node);
    }

    public void outANumType(ANumType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumType(ANumType node)
    {
        inANumType(node);
        if(node.getNum() != null)
        {
            node.getNum().apply(this);
        }
        outANumType(node);
    }

    public void inABoolType(ABoolType node)
    {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        inABoolType(node);
        if(node.getBool() != null)
        {
            node.getBool().apply(this);
        }
        outABoolType(node);
    }

    public void inATextType(ATextType node)
    {
        defaultIn(node);
    }

    public void outATextType(ATextType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATextType(ATextType node)
    {
        inATextType(node);
        if(node.getText() != null)
        {
            node.getText().apply(this);
        }
        outATextType(node);
    }

    public void inAObjectType(AObjectType node)
    {
        defaultIn(node);
    }

    public void outAObjectType(AObjectType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAObjectType(AObjectType node)
    {
        inAObjectType(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAObjectType(node);
    }

    public void inAWhileLoop(AWhileLoop node)
    {
        defaultIn(node);
    }

    public void outAWhileLoop(AWhileLoop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileLoop(AWhileLoop node)
    {
        inAWhileLoop(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAWhileLoop(node);
    }

    public void inAForupLoop(AForupLoop node)
    {
        defaultIn(node);
    }

    public void outAForupLoop(AForupLoop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAForupLoop(AForupLoop node)
    {
        inAForupLoop(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getStart() != null)
        {
            node.getStart().apply(this);
        }
        if(node.getFinish() != null)
        {
            node.getFinish().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAForupLoop(node);
    }

    public void inAFordownLoop(AFordownLoop node)
    {
        defaultIn(node);
    }

    public void outAFordownLoop(AFordownLoop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFordownLoop(AFordownLoop node)
    {
        inAFordownLoop(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getStart() != null)
        {
            node.getStart().apply(this);
        }
        if(node.getFinish() != null)
        {
            node.getFinish().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAFordownLoop(node);
    }

    public void inAIfIfelse(AIfIfelse node)
    {
        defaultIn(node);
    }

    public void outAIfIfelse(AIfIfelse node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfIfelse(AIfIfelse node)
    {
        inAIfIfelse(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAIfIfelse(node);
    }

    public void inAElseIfelse(AElseIfelse node)
    {
        defaultIn(node);
    }

    public void outAElseIfelse(AElseIfelse node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElseIfelse(AElseIfelse node)
    {
        inAElseIfelse(node);
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAElseIfelse(node);
    }

    public void inAFuncsingleCall(AFuncsingleCall node)
    {
        defaultIn(node);
    }

    public void outAFuncsingleCall(AFuncsingleCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncsingleCall(AFuncsingleCall node)
    {
        inAFuncsingleCall(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getParams());
            for(PExpr e : copy)
            {
                e.apply(this);
            }
        }
        outAFuncsingleCall(node);
    }

    public void inAFuncmultiCall(AFuncmultiCall node)
    {
        defaultIn(node);
    }

    public void outAFuncmultiCall(AFuncmultiCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncmultiCall(AFuncmultiCall node)
    {
        inAFuncmultiCall(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getParams());
            for(PExpr e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outAFuncmultiCall(node);
    }

    public void inAClasssingleCall(AClasssingleCall node)
    {
        defaultIn(node);
    }

    public void outAClasssingleCall(AClasssingleCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClasssingleCall(AClasssingleCall node)
    {
        inAClasssingleCall(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outAClasssingleCall(node);
    }

    public void inAClassmultiCall(AClassmultiCall node)
    {
        defaultIn(node);
    }

    public void outAClassmultiCall(AClassmultiCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClassmultiCall(AClassmultiCall node)
    {
        inAClassmultiCall(node);
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        {
            List<PCall> copy = new ArrayList<PCall>(node.getRest());
            for(PCall e : copy)
            {
                e.apply(this);
            }
        }
        outAClassmultiCall(node);
    }

    public void inAConstrcallCall(AConstrcallCall node)
    {
        defaultIn(node);
    }

    public void outAConstrcallCall(AConstrcallCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConstrcallCall(AConstrcallCall node)
    {
        inAConstrcallCall(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getParams());
            for(PExpr e : copy)
            {
                e.apply(this);
            }
        }
        outAConstrcallCall(node);
    }

    public void inAValCall(AValCall node)
    {
        defaultIn(node);
    }

    public void outAValCall(AValCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAValCall(AValCall node)
    {
        inAValCall(node);
        if(node.getVal() != null)
        {
            node.getVal().apply(this);
        }
        outAValCall(node);
    }

    public void inAVarCall(AVarCall node)
    {
        defaultIn(node);
    }

    public void outAVarCall(AVarCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarCall(AVarCall node)
    {
        inAVarCall(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAVarCall(node);
    }

    public void inAMinusExpr(AMinusExpr node)
    {
        defaultIn(node);
    }

    public void outAMinusExpr(AMinusExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExpr(AMinusExpr node)
    {
        inAMinusExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMinusExpr(node);
    }

    public void inAPlusExpr(APlusExpr node)
    {
        defaultIn(node);
    }

    public void outAPlusExpr(APlusExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpr(APlusExpr node)
    {
        inAPlusExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAPlusExpr(node);
    }

    public void inADivideExpr(ADivideExpr node)
    {
        defaultIn(node);
    }

    public void outADivideExpr(ADivideExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivideExpr(ADivideExpr node)
    {
        inADivideExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outADivideExpr(node);
    }

    public void inAMultExpr(AMultExpr node)
    {
        defaultIn(node);
    }

    public void outAMultExpr(AMultExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultExpr(AMultExpr node)
    {
        inAMultExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMultExpr(node);
    }

    public void inAModExpr(AModExpr node)
    {
        defaultIn(node);
    }

    public void outAModExpr(AModExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAModExpr(AModExpr node)
    {
        inAModExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAModExpr(node);
    }

    public void inARelopExpr(ARelopExpr node)
    {
        defaultIn(node);
    }

    public void outARelopExpr(ARelopExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARelopExpr(ARelopExpr node)
    {
        inARelopExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outARelopExpr(node);
    }

    public void inAOrExpr(AOrExpr node)
    {
        defaultIn(node);
    }

    public void outAOrExpr(AOrExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExpr(AOrExpr node)
    {
        inAOrExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAOrExpr(node);
    }

    public void inAAndExpr(AAndExpr node)
    {
        defaultIn(node);
    }

    public void outAAndExpr(AAndExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExpr(AAndExpr node)
    {
        inAAndExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAAndExpr(node);
    }

    public void inAEqualsExpr(AEqualsExpr node)
    {
        defaultIn(node);
    }

    public void outAEqualsExpr(AEqualsExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualsExpr(AEqualsExpr node)
    {
        inAEqualsExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqualsExpr(node);
    }

    public void inANotequalsExpr(ANotequalsExpr node)
    {
        defaultIn(node);
    }

    public void outANotequalsExpr(ANotequalsExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotequalsExpr(ANotequalsExpr node)
    {
        inANotequalsExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outANotequalsExpr(node);
    }

    public void inAGreaterExpr(AGreaterExpr node)
    {
        defaultIn(node);
    }

    public void outAGreaterExpr(AGreaterExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterExpr(AGreaterExpr node)
    {
        inAGreaterExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGreaterExpr(node);
    }

    public void inALessExpr(ALessExpr node)
    {
        defaultIn(node);
    }

    public void outALessExpr(ALessExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessExpr(ALessExpr node)
    {
        inALessExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALessExpr(node);
    }

    public void inAGreaterequalsExpr(AGreaterequalsExpr node)
    {
        defaultIn(node);
    }

    public void outAGreaterequalsExpr(AGreaterequalsExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterequalsExpr(AGreaterequalsExpr node)
    {
        inAGreaterequalsExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGreaterequalsExpr(node);
    }

    public void inALessequalsExpr(ALessequalsExpr node)
    {
        defaultIn(node);
    }

    public void outALessequalsExpr(ALessequalsExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessequalsExpr(ALessequalsExpr node)
    {
        inALessequalsExpr(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALessequalsExpr(node);
    }

    public void inAUnaryExpr(AUnaryExpr node)
    {
        defaultIn(node);
    }

    public void outAUnaryExpr(AUnaryExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAUnaryExpr(AUnaryExpr node)
    {
        inAUnaryExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAUnaryExpr(node);
    }

    public void inANotExpr(ANotExpr node)
    {
        defaultIn(node);
    }

    public void outANotExpr(ANotExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExpr(ANotExpr node)
    {
        inANotExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outANotExpr(node);
    }

    public void inAValExpr(AValExpr node)
    {
        defaultIn(node);
    }

    public void outAValExpr(AValExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAValExpr(AValExpr node)
    {
        inAValExpr(node);
        if(node.getVal() != null)
        {
            node.getVal().apply(this);
        }
        outAValExpr(node);
    }

    public void inACallExpr(ACallExpr node)
    {
        defaultIn(node);
    }

    public void outACallExpr(ACallExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallExpr(ACallExpr node)
    {
        inACallExpr(node);
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outACallExpr(node);
    }

    public void inAIdExpr(AIdExpr node)
    {
        defaultIn(node);
    }

    public void outAIdExpr(AIdExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdExpr(AIdExpr node)
    {
        inAIdExpr(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdExpr(node);
    }

    public void inANumVal(ANumVal node)
    {
        defaultIn(node);
    }

    public void outANumVal(ANumVal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumVal(ANumVal node)
    {
        inANumVal(node);
        if(node.getNumval() != null)
        {
            node.getNumval().apply(this);
        }
        outANumVal(node);
    }

    public void inATextVal(ATextVal node)
    {
        defaultIn(node);
    }

    public void outATextVal(ATextVal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATextVal(ATextVal node)
    {
        inATextVal(node);
        if(node.getTextval() != null)
        {
            node.getTextval().apply(this);
        }
        outATextVal(node);
    }

    public void inABoolVal(ABoolVal node)
    {
        defaultIn(node);
    }

    public void outABoolVal(ABoolVal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolVal(ABoolVal node)
    {
        inABoolVal(node);
        if(node.getBoolval() != null)
        {
            node.getBoolval().apply(this);
        }
        outABoolVal(node);
    }

    public void inAConstrVal(AConstrVal node)
    {
        defaultIn(node);
    }

    public void outAConstrVal(AConstrVal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConstrVal(AConstrVal node)
    {
        inAConstrVal(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PExpr> copy = new ArrayList<PExpr>(node.getParam());
            for(PExpr e : copy)
            {
                e.apply(this);
            }
        }
        outAConstrVal(node);
    }

    public void inAVardclStmt(AVardclStmt node)
    {
        defaultIn(node);
    }

    public void outAVardclStmt(AVardclStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVardclStmt(AVardclStmt node)
    {
        inAVardclStmt(node);
        if(node.getPdcl() != null)
        {
            node.getPdcl().apply(this);
        }
        outAVardclStmt(node);
    }

    public void inAAssignmentStmt(AAssignmentStmt node)
    {
        defaultIn(node);
    }

    public void outAAssignmentStmt(AAssignmentStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignmentStmt(AAssignmentStmt node)
    {
        inAAssignmentStmt(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAAssignmentStmt(node);
    }

    public void inAForupStmt(AForupStmt node)
    {
        defaultIn(node);
    }

    public void outAForupStmt(AForupStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAForupStmt(AForupStmt node)
    {
        inAForupStmt(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAForupStmt(node);
    }

    public void inAFordownStmt(AFordownStmt node)
    {
        defaultIn(node);
    }

    public void outAFordownStmt(AFordownStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFordownStmt(AFordownStmt node)
    {
        inAFordownStmt(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAFordownStmt(node);
    }

    public void inAWhileStmt(AWhileStmt node)
    {
        defaultIn(node);
    }

    public void outAWhileStmt(AWhileStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileStmt(AWhileStmt node)
    {
        inAWhileStmt(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAWhileStmt(node);
    }

    public void inAIfstmtStmt(AIfstmtStmt node)
    {
        defaultIn(node);
    }

    public void outAIfstmtStmt(AIfstmtStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfstmtStmt(AIfstmtStmt node)
    {
        inAIfstmtStmt(node);
        if(node.getIfstmt() != null)
        {
            node.getIfstmt().apply(this);
        }
        outAIfstmtStmt(node);
    }

    public void inAFunccallStmt(AFunccallStmt node)
    {
        defaultIn(node);
    }

    public void outAFunccallStmt(AFunccallStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunccallStmt(AFunccallStmt node)
    {
        inAFunccallStmt(node);
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outAFunccallStmt(node);
    }

    public void inAClasscallStmt(AClasscallStmt node)
    {
        defaultIn(node);
    }

    public void outAClasscallStmt(AClasscallStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClasscallStmt(AClasscallStmt node)
    {
        inAClasscallStmt(node);
        if(node.getCall() != null)
        {
            node.getCall().apply(this);
        }
        outAClasscallStmt(node);
    }

    public void inAEventdclStmt(AEventdclStmt node)
    {
        defaultIn(node);
    }

    public void outAEventdclStmt(AEventdclStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEventdclStmt(AEventdclStmt node)
    {
        inAEventdclStmt(node);
        if(node.getPdcl() != null)
        {
            node.getPdcl().apply(this);
        }
        outAEventdclStmt(node);
    }

    public void inAIfIfstmt(AIfIfstmt node)
    {
        defaultIn(node);
    }

    public void outAIfIfstmt(AIfIfstmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfIfstmt(AIfIfstmt node)
    {
        inAIfIfstmt(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getElsestmt() != null)
        {
            node.getElsestmt().apply(this);
        }
        outAIfIfstmt(node);
    }

    public void inAElseElsestmt(AElseElsestmt node)
    {
        defaultIn(node);
    }

    public void outAElseElsestmt(AElseElsestmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElseElsestmt(AElseElsestmt node)
    {
        inAElseElsestmt(node);
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        outAElseElsestmt(node);
    }

    public void inAElseifElsestmt(AElseifElsestmt node)
    {
        defaultIn(node);
    }

    public void outAElseifElsestmt(AElseifElsestmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElseifElsestmt(AElseifElsestmt node)
    {
        inAElseifElsestmt(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getElsestmt() != null)
        {
            node.getElsestmt().apply(this);
        }
        outAElseifElsestmt(node);
    }
}
