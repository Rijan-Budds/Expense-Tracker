defmodule ExpenseTrackerWeb.ExpenseController do
  use ExpenseTrackerWeb, :controller

  alias ExpenseTracker.Finances
  alias ExpenseTracker.Finances.Expense

  def delete(conn, %{"id" => id}) do
    expense = Finances.get_expense!(id)

    {:ok, _expense} = Finances.delete_expense(expense)

    conn
    |> put_flash(:info, "Expense deleted successfully.")
    |> redirect(to: ~p"/expenses")
  end

  def show(conn, %{"id" => id}) do
    expense = Finances.get_expense!(id)
    render(conn, :show, expense: expense)
  end

  def index(conn, _params) do
    expenses = Finances.list_expenses()
    render(conn, :index, expenses: expenses)
  end

  def new(conn, _params) do
    changeset = Finances.change_expense(%Expense{})
    render(conn, :new, changeset: changeset)
  end

  def edit(conn, %{"id" => id}) do
    expense = Finances.get_expense!(id)
    changeset = Finances.change_expense(expense)
    render(conn, :edit, expense: expense, changeset: changeset)
  end

  def update(conn, %{"id" => id, "expense" => expense_params}) do
    expense = Finances.get_expense!(id)

    case Finances.update_expense(expense, expense_params) do
      {:ok, expense} ->
        conn
        |> put_flash(:info, "Expense updated successfully.")
        |> redirect(to: ~p"/expenses/#{expense.id}")

      {:error, %Ecto.Changeset{} = changeset} ->
        render(conn, :edit, expense: expense, changeset: changeset)
    end
  end

  def create(conn, %{"expense" => expense_params}) do
    case Finances.create_expense(expense_params) do
      {:ok, _expense} ->
        conn
        |> put_flash(:info, "Expense created successfully.")
        |> redirect(to: ~p"/expenses")

      {:error, %Ecto.Changeset{} = changeset} ->
        render(conn, :new, changeset: changeset)
    end
  end

  # Add other CRUD actions as needed
end
