defmodule ExpenseTracker.Repo do
  use Ecto.Repo,
    otp_app: :expense_tracker,
    adapter: Ecto.Adapters.Postgres
end
