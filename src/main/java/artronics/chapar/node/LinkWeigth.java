package artronics.chapar.node;

final class LinkWeigth implements Weigth
{
    private int qualityValue;

    protected LinkWeigth(int qualityValue)
    {
        this.qualityValue = qualityValue;
    }

    public static int compare(int x, int y)
    {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    @Override
    public int getQualityValue()
    {
        return this.qualityValue;
    }

    @Override
    public void setQualityValue(int qualityValue)
    {
        this.qualityValue = qualityValue;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof LinkWeigth) {
            return qualityValue == ((LinkWeigth) o).getQualityValue();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(qualityValue);
    }

    @Override
    public int compareTo(Weigth anotherWeigth)
    {
        return compare(this.qualityValue, anotherWeigth.getQualityValue());
    }
}
