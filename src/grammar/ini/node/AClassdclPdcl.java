/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.node;

import java.util.*;
import grammar.ini.analysis.*;

@SuppressWarnings("nls")
public final class AClassdclPdcl extends PPdcl
{
    private TId _id_;
    private final LinkedList<PBody> _body_ = new LinkedList<PBody>();

    public AClassdclPdcl()
    {
        // Constructor
    }

    public AClassdclPdcl(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") List<?> _body_)
    {
        // Constructor
        setId(_id_);

        setBody(_body_);

    }

    @Override
    public Object clone()
    {
        return new AClassdclPdcl(
            cloneNode(this._id_),
            cloneList(this._body_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClassdclPdcl(this);
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

    public LinkedList<PBody> getBody()
    {
        return this._body_;
    }

    public void setBody(List<?> list)
    {
        for(PBody e : this._body_)
        {
            e.parent(null);
        }
        this._body_.clear();

        for(Object obj_e : list)
        {
            PBody e = (PBody) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._body_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._body_);
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

        if(this._body_.remove(child))
        {
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

        for(ListIterator<PBody> i = this._body_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PBody) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
