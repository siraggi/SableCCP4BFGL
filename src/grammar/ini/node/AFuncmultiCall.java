/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.node;

import java.util.*;
import grammar.ini.analysis.*;

@SuppressWarnings("nls")
public final class AFuncmultiCall extends PCall
{
    private TId _id_;
    private final LinkedList<PExpr> _params_ = new LinkedList<PExpr>();
    private PCall _call_;

    public AFuncmultiCall()
    {
        // Constructor
    }

    public AFuncmultiCall(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") List<?> _params_,
        @SuppressWarnings("hiding") PCall _call_)
    {
        // Constructor
        setId(_id_);

        setParams(_params_);

        setCall(_call_);

    }

    @Override
    public Object clone()
    {
        return new AFuncmultiCall(
            cloneNode(this._id_),
            cloneList(this._params_),
            cloneNode(this._call_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFuncmultiCall(this);
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public LinkedList<PExpr> getParams()
    {
        return this._params_;
    }

    public void setParams(List<?> list)
    {
        for(PExpr e : this._params_)
        {
            e.parent(null);
        }
        this._params_.clear();

        for(Object obj_e : list)
        {
            PExpr e = (PExpr) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._params_.add(e);
        }
    }

    public PCall getCall()
    {
        return this._call_;
    }

    public void setCall(PCall node)
    {
        if(this._call_ != null)
        {
            this._call_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._call_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._params_)
            + toString(this._call_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._params_.remove(child))
        {
            return;
        }

        if(this._call_ == child)
        {
            this._call_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        for(ListIterator<PExpr> i = this._params_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PExpr) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._call_ == oldChild)
        {
            setCall((PCall) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
