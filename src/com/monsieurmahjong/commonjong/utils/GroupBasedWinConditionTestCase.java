package com.monsieurmahjong.commonjong.utils;

import java.util.List;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class GroupBasedWinConditionTestCase
{
    private String name;
    private List<TileGroup> groups;
    private boolean expectsSuccess;

    public GroupBasedWinConditionTestCase(String name, List<TileGroup> groups, boolean expectsSuccess)
    {
        this.name = name;
        this.groups = groups;
        this.expectsSuccess = expectsSuccess;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<TileGroup> getGroups()
    {
        return groups;
    }

    public void setGroups(List<TileGroup> groups)
    {
        this.groups = groups;
    }

    public boolean isExpectsSuccess()
    {
        return expectsSuccess;
    }

    public void setExpectsSuccess(boolean expectsSuccess)
    {
        this.expectsSuccess = expectsSuccess;
    }
}
