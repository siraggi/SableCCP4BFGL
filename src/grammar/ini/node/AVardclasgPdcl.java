/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.node;

import grammar.ini.analysis.*;

@SuppressWarnings("nls")
public final class AVardclasgPdcl extends PPdcl
{
    private PType _type_;
    private TId _id_;
    private PExpr _expr_;

    public AVardclasgPdcl()
    {
        // Constructor
    }

    public AVardclasgPdcl(
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") PExpr _expr_)
    {
        // Constructor
        setType(_type_);

        setId(_id_);

        setExpr(_expr_);

    }

    @Override
    public Object clone()
    {
        return new AVardclasgPdcl(
            cloneNode(this._type_),
            cloneNode(this._id_),
            cloneNode(this._expr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVardclasgPdcl(this);
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
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

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._type_)
            + toString(this._id_)
            + toString(this._expr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
