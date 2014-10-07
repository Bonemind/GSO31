package fontys.time;

/**
 * @author Michon
 */
public class Period implements IPeriod {
    private ITime begin;
    private ITime end;

    public Period(ITime bt, ITime et) {
        begin = bt;
        end = et;
    }

    @Override
    public ITime getBeginTime() {
        return begin;
    }

    @Override
    public ITime getEndTime() {
        return end;
    }

    @Override
    public int length() {
        return Math.abs(begin.difference(end));
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        begin = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        end = endTime;
    }

    @Override
    public void move(int minutes) {
        begin = begin.plus(minutes);
        end = end.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        end = end.plus(minutes);
    }

    @Override
    public boolean isPartOf(IPeriod period) {
        return period.getBeginTime().compareTo(begin) <= 0 && period.getEndTime().compareTo(end) >= 0;
    }

    @Override
    public IPeriod unionWith(IPeriod period) {
        if (period.getBeginTime().compareTo(end) < 0 && period.getEndTime().compareTo(begin) > 0) {
            return null;
        }
        return new Period(
            period.getBeginTime().compareTo(begin) < 0 ? begin : period.getBeginTime(),
            period.getEndTime().compareTo(end) > 0 ? end : period.getEndTime()
        );
    }

    @Override
    public IPeriod intersectionWith(IPeriod period) {
        if (period.getBeginTime().compareTo(end) < 0 && period.getEndTime().compareTo(begin) > 0) {
            return null;
        }
        return new Period(
                period.getBeginTime().compareTo(begin) > 0 ? begin : period.getBeginTime(),
                period.getEndTime().compareTo(end) < 0 ? end : period.getEndTime()
        );
    }
}
