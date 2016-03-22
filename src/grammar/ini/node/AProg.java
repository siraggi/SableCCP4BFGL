/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.node;

import java.util.*;
import grammar.ini.analysis.*;

@SuppressWarnings("nls")
public final class AProg extends PProg
{
    private final LinkedList<PPdcl> _globaldcl_ = new LinkedList<PPdcl>();
    private PPdcl _maindcl_;
    private final LinkedList<PPdcl> _classdcl_ = new LinkedList<PPdcl>();

    public AProg()
    {
        // Constructor
    }

    public AProg(
        @SuppressWarnings("hiding") List<?> _globaldcl_,
        @SuppressWarnings("hiding") PPdcl _maindcl_,
        @SuppressWarnings("hiding") List<?> _classdcl_)
    {
        // Constructor
        setGlobaldcl(_globaldcl_);

        setMaindcl(_maindcl_);

        setClassdcl(_classdcl_);

    }

    @Override
    public Object clone()
    {
        return new AProg(
            cloneList(this._globaldcl_),
            cloneNode(this._maindcl_),
            cloneList(this._classdcl_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProg(this);
    }

    public LinkedList<PPdcl> getGlobaldcl()
    {
        return this._globaldcl_;
    }

    public void setGlobaldcl(List<?> list)
    {
        for(PPdcl e : this._globaldcl_)
        {
            e.parent(null);
        }
        this._globaldcl_.clear();

        for(Object obj_e : list)
        {
            PPdcl e = (PPdcl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._globaldcl_.add(e);
        }
    }

    public PPdcl getMaindcl()
    {
        return this._maindcl_;
    }

    public void setMaindcl(PPdcl node)
    {
        if(this._maindcl_ != null)
        {
            this._maindcl_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._maindcl_ = node;
    }

    public LinkedList<PPdcl> getClassdcl()
    {
        return this._classdcl_;
    }

    public void setClassdcl(List<?> list)
    {
        for(PPdcl e : this._classdcl_)
        {
            e.parent(null);
        }
        this._classdcl_.clear();

        for(Object obj_e : list)
        {
            PPdcl e = (PPdcl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._classdcl_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._globaldcl_)
            + toString(this._maindcl_)
            + toString(this._classdcl_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._globaldcl_.remove(child))
        {
            return;
        }

        if(this._maindcl_ == child)
        {
            this._maindcl_ = null;
            return;
        }

        if(this._classdcl_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PPdcl> i = this._globaldcl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PPdcl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._maindcl_ == oldChild)
        {
            setMaindcl((PPdcl) newChild);
            return;
        }

        for(ListIterator<PPdcl> i = this._classdcl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PPdcl) newChild);
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
