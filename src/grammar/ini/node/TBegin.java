/* This file was generated by SableCC (http://www.sablecc.org/). */

package grammar.ini.node;

import grammar.ini.analysis.*;

@SuppressWarnings("nls")
public final class TBegin extends Token
{
    public TBegin()
    {
        super.setText("begin");
    }

    public TBegin(int line, int pos)
    {
        super.setText("begin");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TBegin(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBegin(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TBegin text.");
    }
}
