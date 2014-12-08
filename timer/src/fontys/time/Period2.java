package fontys.time;

/**
 * Created by Subhi on 7-10-2014.
 */
public class Period2 implements IPeriod {
    private ITime time;
    private long duration;

    public Period2(ITime bt, ITime et) {
        time = bt;
        duration = et.difference(bt);
    }

    /**
     * @return the begin time of this period
     */
    @Override
    public ITime getBeginTime() {
        return time;
    }

    /**
     * @return the end time of this period
     */
    @Override
    public ITime getEndTime() {
        return time.plus((int) duration);
    }

    /**
     * @return the length of this period expressed in minutes (always positive)
     */
    @Override
    public int length() {
        return (int) duration;
    }

    /**
     * beginTime will be the new begin time of this period
     *
     * @param beginTime must be earlier than the current end time
     *                  of this period
     */
    @Override
    public void setBeginTime(ITime beginTime) {
        time = beginTime;
    }

    /**
     * endTime will be the new end time of this period
     *
     * @param endTime
     */
    @Override
    public void setEndTime(ITime endTime) {
        duration = (long) endTime.difference(time);
    }

    /**
     * the begin and end time of this period will be moved up both with [minutes]
     * minutes
     *
     * @param minutes (a negative value is allowed)
     */
    @Override
    public void move(int minutes) {
        time = time.plus(minutes);
    }

    /**
     * the end time of this period will be moved up with [minutes] minutes
     *
     * @param minutes minutes + length of this period must be positive
     */
    @Override
    public void changeLengthWith(int minutes) {
        long tempDuration = duration + minutes;
        if (tempDuration >= 0) {
            duration = tempDuration;
        }
    }

    /**
     * @param period
     * @return true if all moments within this period are included within [period],
     * otherwise false
     */
    @Override
    public boolean isPartOf(IPeriod period) {
        return time.compareTo(period.getBeginTime()) >= 0 && this.getEndTime().compareTo(period.getEndTime()) <= 0;
    }

    /**
     * @param period
     * @return if this period and [period] are consecutive or possess a
     * common intersection, then the smallest period p will be returned,
     * for which this period and [period] are part of p,
     * otherwise null will be returned
     */
    @Override
    public IPeriod unionWith(IPeriod period) {
        if (time.plus((int) duration).compareTo(period.getBeginTime()) >= 0){
            ITime startTime = time.compareTo(period.getBeginTime()) < 0 ? time : period.getBeginTime();
            ITime endTime = time.plus((int) duration).compareTo(period.getEndTime()) > 0 ? time.plus((int) duration) : period.getEndTime();
            return new Period2(startTime, endTime);
        }
        return null;
    }

    /**
     * @param period
     * @return the largest period which is part of this period
     * and [period] will be returned; if the intersection is empty null will
     * be returned
     */
    @Override
    public IPeriod intersectionWith(IPeriod period) {
        if (this.isPartOf(period)) {
            return this;
        } else if (period.isPartOf(this)) {
            return period;
        }
        if (time.plus((int) duration).compareTo(period.getBeginTime()) >= 0){


            ITime startTime = time.compareTo(period.getBeginTime()) > 0 ? time : period.getBeginTime();
            ITime endTime = time.plus((int) duration).compareTo(period.getEndTime()) < 0 ? time.plus((int) duration) : period.getEndTime();
            return new Period2(startTime, endTime);
        }
        return null;
    }
}
